package com.tosslab.homework.v1.subscribe.repository;

import com.tosslab.homework.v1.subscribe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
