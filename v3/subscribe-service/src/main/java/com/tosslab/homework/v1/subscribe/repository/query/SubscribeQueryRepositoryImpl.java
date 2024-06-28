package com.tosslab.homework.v1.subscribe.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tosslab.homework.v1.subscribe.domain.QMember.*;
import static com.tosslab.homework.v1.subscribe.domain.QSubscribe.*;

@Repository
@RequiredArgsConstructor
public class SubscribeQueryRepositoryImpl implements SubscribeQueryRepository{

    private final JPAQueryFactory query;


    @Override
    public List<String> loadAllWebhookUrls(Long targetId) {
        return query
                .select(member.webhookUrl)
                .distinct()
                .from(member)
                .join(subscribe)
                .on(subscribe.targetId.eq(targetId))
                .where(member.memberId.ne(targetId))
                .fetch();
    }
}
