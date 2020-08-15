package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.member.IntegrationEntity;
import com.soriel.music.springboot.domain.member.IntegrationRepository;
import com.soriel.music.springboot.web.dto.member.CustomIntegrationDto;
import com.soriel.music.springboot.web.dto.member.IntegrationDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private IntegrationRepository integrationRepository;

    public IntegrationEntity joinUser(IntegrationDto integrationDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        integrationDto.setUpwd(passwordEncoder.encode(integrationDto.getUpwd()));

        return integrationRepository.save(integrationDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<IntegrationEntity> integrationEntityOptional = integrationRepository.findByName(username);
        IntegrationEntity integrationEntity = integrationEntityOptional.orElse(null);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(integrationEntity.getRoleKey()));

        return new CustomIntegrationDto(integrationEntity, integrationEntity.getName(), integrationEntity.getUpwd(), authorities);
    }

    public Long getMemberInfo(String username) {
        Optional<IntegrationEntity> integrationEntityOptional =  integrationRepository.findByName(username);
        IntegrationEntity integrationEntity = integrationEntityOptional.orElse(null);

        return integrationEntity != null ? integrationEntity.getId() : null;
    }

    public boolean verifyId(String name) {
        Optional<IntegrationEntity> integrationEntityOptional = integrationRepository.findByName(name);
        try{
            IntegrationEntity integrationEntity = integrationEntityOptional.get();
            if(integrationEntity != null) {
                return false;
            }
        } catch (NoSuchElementException nse) {
            return true;
        }
        return false;
    }
}
