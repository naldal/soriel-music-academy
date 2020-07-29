package com.soriel.music.springboot.domain.soriel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IntegrationRepository extends JpaRepository<IntegrationEntity, Long> {
    Optional<IntegrationEntity> findByName(String userEmail);
}
