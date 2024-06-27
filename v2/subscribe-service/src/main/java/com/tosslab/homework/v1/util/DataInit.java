package com.tosslab.homework.v1.util;

import com.tosslab.homework.v1.subscribe.domain.Member;
import com.tosslab.homework.v1.subscribe.domain.Subscribe;
import com.tosslab.homework.v1.subscribe.repository.MemberRepository;
import com.tosslab.homework.v1.subscribe.repository.SubscribeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class DataInit {

    private final MemberRepository memberRepository;
    private final SubscribeRepository subscribeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        final List<Member> memberList = List.of(
                Member.of("rex.seo@tosslab.com", "Dev Seo Rex",
                        "https://wh.jandi.com/connect-api/webhook/279/f9ae0c362f0a03e43e9101f4b7760b87"),
                Member.of("rex.subscriber1@tosslab.com", "Rex Subscriber 1",
                        "https://wh.jandi.com/connect-api/webhook/279/004bead40f9d0acd5d9c2821405ca3b9"),
                Member.of("rex.subscriber2@tosslab.com", "Rex Subscriber 2",
                        "https://wh.jandi.com/connect-api/webhook/279/9558657300c1f356dd9cd2a7b051131c"),
                Member.of("rex.subscriber3@tosslab.com", "Rex Subscriber 3",
                        "https://wh.jandi.com/connect-api/webhook/279/bb42372e4e7f5b53a9bf48116d1fd7c7")
        );

        memberRepository.saveAll(memberList);

        final List<Subscribe> subscribeList = List.of(
                Subscribe.of(memberList.get(0).getMemberId(), memberList.get(1).getMemberId()),
                Subscribe.of(memberList.get(0).getMemberId(), memberList.get(2).getMemberId()),
                Subscribe.of(memberList.get(0).getMemberId(), memberList.get(3).getMemberId())
        );

        subscribeRepository.saveAll(subscribeList);

    }

}
