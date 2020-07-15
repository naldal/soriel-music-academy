package com.soriel.music.springboot.domain.soriel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoRepository extends JpaRepository<KakaoEntity, Long> {
    Optional<KakaoEntity> findByName(String userEmail);
}
