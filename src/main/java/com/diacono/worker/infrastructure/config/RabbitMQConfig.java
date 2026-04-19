package com.diacono.worker.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${mq.queue.evento}")
    private String queueName;

    @Value("${mq.exchange.evento}")
    private String exchangeName;

    @Value("${mq.routing-key.evento}")
    private String routingKey;

    // 1. Define a Fila
    @Bean
    public Queue queue() {
        return new Queue(queueName, true); // true = durável
    }

    // 2. Define a Exchange do tipo Direct Exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    // 3. Faz o "Binding" (Liga a fila na exchange através da routing key)
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    // 4. Opcional mas interessante em fazer. Define o Message Converter para converter objetos Java em JSON e vice-versa
    // Sem isso, o RabbitTemplate vai tentar converter o objeto em bytes , tornando o debug bem mais difícil
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
