package com.diacono.worker;

import com.diacono.worker.infrastructure.messaging.RabbitMQConsumer;
import com.diacono.worker.infrastructure.messaging.dto.EventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class WorkerApplication {

	private static final Logger log = LoggerFactory.getLogger(WorkerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
	}

	@Bean
	CommandLineRunner bootstrapTest(RabbitMQConsumer rabbitMQConsumer) {
		return args -> {
			EventDTO fakeEvent = new EventDTO(
					UUID.randomUUID(),
					UUID.fromString("2a6f6f14-c429-46e5-b9c9-9ac38f5ecb3f"),
					"Culto de Jovens",
					"Jovens",
					LocalDateTime.now().plusMinutes(10),
					LocalDateTime.now().plusHours(2),
					"Evento de teste disparado no bootstrap da aplicacao",
					new BigDecimal("0.00"),
					"Auditorio Sede"
			);

			log.info("Executando teste de bootstrap: simulando mensagem recebida.");
			rabbitMQConsumer.processMessage(fakeEvent);
		};
	}

}
