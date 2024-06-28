package com.tosslab.homework.v1.subscribe.repository.query;

import java.util.List;

public interface SubscribeQueryRepository {

    List<String> loadAllWebhookUrls(Long targetId);
}
