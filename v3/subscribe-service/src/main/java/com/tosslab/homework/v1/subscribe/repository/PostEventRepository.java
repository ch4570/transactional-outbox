package com.tosslab.homework.v1.subscribe.repository;

import com.tosslab.homework.v1.subscribe.domain.PostEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostEventRepository extends JpaRepository<PostEvent, UUID> {

}
