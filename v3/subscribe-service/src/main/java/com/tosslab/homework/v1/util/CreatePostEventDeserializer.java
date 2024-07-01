package com.tosslab.homework.v1.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosslab.homework.v1.event.object.CreatePostEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePostEventDeserializer implements Deserializer<CreatePostEvent> {

    private final ObjectMapper objectMapper;

    @Override
    public CreatePostEvent deserialize(String topic, byte[] data) {
        try {

            log.info("이벤트 수신");

            JsonNode extractedData = objectMapper.readTree(data)
                    .get("payload")
                    .get("after");


            log.error("새로운 이벤트 = [{}]", extractedData.toPrettyString());

            return objectMapper.treeToValue(extractedData, CreatePostEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
