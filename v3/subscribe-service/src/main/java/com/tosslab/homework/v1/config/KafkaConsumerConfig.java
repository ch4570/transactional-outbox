package com.tosslab.homework.v1.config;

import com.tosslab.homework.v1.config.properties.KafkaConsumerProperties;
import com.tosslab.homework.v1.event.object.CreatePostEvent;
import com.tosslab.homework.v1.util.CreatePostEventDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaConsumerProperties kafkaConsumerProperties;
    private final CreatePostEventDeserializer createPostEventDeserializer;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, CreatePostEvent> concurrentKafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, CreatePostEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    ConsumerFactory<String, CreatePostEvent> consumerFactory() {

        Map<String, Object> consumerConfigs = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getBootstrapServers(),
                ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getGroupId(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CreatePostEventDeserializer.class,
                JsonDeserializer.TRUSTED_PACKAGES, "*",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"
        );

        return new DefaultKafkaConsumerFactory<>(consumerConfigs, new StringDeserializer(), new ErrorHandlingDeserializer<>(createPostEventDeserializer));
    }

}
