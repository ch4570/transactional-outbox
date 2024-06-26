package com.tosslab.homework.v1.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class KafkaProducerProperties {

    private final String bootstrapServers;
}
