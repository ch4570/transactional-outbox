package com.tosslab.homework.v1.subscribe.repository;

import com.tosslab.homework.v1.subscribe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m.nickname from Member m where m.memberId = :memberId")
    String loadMemberNickname(@Param("memberId") Long memberId);
}
