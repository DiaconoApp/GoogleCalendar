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

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
	}
}
