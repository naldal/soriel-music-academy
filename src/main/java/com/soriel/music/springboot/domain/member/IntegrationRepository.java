package com.soriel.music.springboot.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IntegrationRepository extends JpaRepository<IntegrationEntity, Long> {
    Optional<IntegrationEntity> findByName(String userEmail);
}
