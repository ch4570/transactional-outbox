package com.tosslab.homework.v1.subscribe.service.usecase;

import com.tosslab.homework.v1.subscribe.service.dto.SendNotificationServiceRequest;

public interface SendNotificationUseCase {

    void sendNotification(SendNotificationServiceRequest serviceRequest);
}
