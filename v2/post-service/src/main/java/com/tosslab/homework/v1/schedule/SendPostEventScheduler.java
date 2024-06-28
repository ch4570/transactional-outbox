package com.tosslab.homework.v1.schedule;

import com.tosslab.homework.v1.post.domain.PostEventOutbox;
import com.tosslab.homework.v1.post.event.object.CreatePostEvent;
import com.tosslab.homework.v1.post.service.usecase.LoadPostEventOutboxUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class SendPostEventScheduler {

    private final LoadPostEventOutboxUseCase loadPostEventOutboxUseCase;
    private final KafkaTemplate<String, CreatePostEvent> kafkaTemplate;

    @Scheduled(fixedRate = 1000)
    void sendPostEvent() {
        log.info("1초 마다 스케쥴링 테스트 : 수행 시간 = [{}]", LocalDateTime.now());
        List<PostEventOutbox> postEventOutboxList = loadPostEventOutboxUseCase.loadBeforeProcessingEvents();

        // 메시지 발송이 되지 않은 Outbox List를 순회하면서, 메시지 발송 처리
        postEventOutboxList.forEach(postEventOutbox -> {
            final CreatePostEvent createPostEvent =
                    CreatePostEvent.of(postEventOutbox.getEventId(), postEventOutbox.getPostId(),
                            postEventOutbox.getAuthorId(), postEventOutbox.getTitle());

            kafkaTemplate.send("post-create", createPostEvent);
            // 전송된 메시지에 대해, 상태를 "완료"로 수정한다.
            postEventOutbox.completeEvent();
        });

        log.info("처리한 이벤트 개수 = [{}], 종료 시간 = [{}]", postEventOutboxList.size(), LocalDateTime.now());
    }
}

