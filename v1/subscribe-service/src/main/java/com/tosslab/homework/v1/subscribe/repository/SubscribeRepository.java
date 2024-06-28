package com.tosslab.homework.v1.subscribe.repository;

import com.tosslab.homework.v1.subscribe.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
}
