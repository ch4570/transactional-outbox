package com.tosslab.homework.v1.subscribe.service;

import com.tosslab.homework.v1.client.dto.WebhookCommand;
import com.tosslab.homework.v1.subscribe.domain.PostEvent;
import com.tosslab.homework.v1.subscribe.repository.MemberRepository;
import com.tosslab.homework.v1.subscribe.repository.PostEventRepository;
import com.tosslab.homework.v1.subscribe.repository.query.SubscribeQueryRepository;
import com.tosslab.homework.v1.subscribe.service.dto.SendNotificationServiceRequest;
import com.tosslab.homework.v1.subscribe.service.usecase.SendNotificationUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SendNotificationService implements SendNotificationUseCase {

    private final SubscribeQueryRepository queryRepository;
    private final PostEventRepository postEventRepository;
    private final MemberRepository memberRepository;
    private final RestClient restClient;

    @Override
    public void sendNotification(SendNotificationServiceRequest serviceRequest) {
        final Optional<PostEvent> findEvent = postEventRepository.findById(serviceRequest.eventId());

        if (findEvent.isEmpty()) {
            final PostEvent postEvent = PostEvent.of(serviceRequest.eventId(), serviceRequest.authorId());
            final String authorNickname = memberRepository.loadMemberNickname(serviceRequest.authorId());

            final WebhookCommand webhookCommand = WebhookCommand.of(serviceRequest.title(), authorNickname);

            queryRepository.loadAllWebhookUrls(serviceRequest.authorId())
                            .forEach(webHookUrl -> {
                                log.info("요청 시도 중 = {}", webHookUrl);

                                int result = restClient.post()
                                        .uri(webHookUrl)
                                        .body(webhookCommand)
                                        .retrieve()
                                        .toBodilessEntity().getStatusCode().value();

                                log.info("상태 코드 = {}", result);
                            });

            postEventRepository.save(postEvent);
        }

    }
}
