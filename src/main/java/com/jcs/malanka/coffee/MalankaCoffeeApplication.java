package com.jcs.malanka.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Publish package to DockerHub Action
// TODO: ELK
// TODO: P + G
// TODO: OpenTelemetry + Zipkin
// TODO: Actuator
// TODO: Kafka/RabbitMQ
@SpringBootApplication
public class MalankaCoffeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MalankaCoffeeApplication.class, args);
    }

}
