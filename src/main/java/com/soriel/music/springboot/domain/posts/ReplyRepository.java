package com.soriel.music.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    @Query(value = "SELECT * FROM reply WHERE post_id = ?1", nativeQuery = true)
    Optional<ReplyEntity> findByPostId(Long aLong);
}
