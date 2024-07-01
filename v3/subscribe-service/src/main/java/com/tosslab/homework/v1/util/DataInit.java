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
                        "https://wh.jandi.io/connect-api/webhook/279/cf9ffaaa8b14c944700cc51d7b27f90a"),
                Member.of("rex.subscriber1@tosslab.com", "Rex Subscriber 1",
                        "https://wh.jandi.io/connect-api/webhook/279/36b43632c7f914e75a835bc06949b252"),
                Member.of("rex.subscriber2@tosslab.com", "Rex Subscriber 2",
                        "https://wh.jandi.io/connect-api/webhook/279/41bdf4f77bf627750404a1e6ffc8543b"),
                Member.of("rex.subscriber3@tosslab.com", "Rex Subscriber 3",
                        "https://wh.jandi.io/connect-api/webhook/279/79e7f1ddc77fc33daa63a6cf70ce97d5")
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
