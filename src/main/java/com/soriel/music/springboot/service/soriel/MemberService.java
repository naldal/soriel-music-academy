package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.Role;
import com.soriel.music.springboot.domain.soriel.IntegrationEntity;
import com.soriel.music.springboot.domain.soriel.IntegrationRepository;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private IntegrationRepository integrationRepository;

    public IntegrationEntity joinUser(IntegrationDto integrationDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        integrationDto.setUpwd(passwordEncoder.encode(integrationDto.getUpwd()));

        if (integrationDto.getName().equals("admin")) {

            System.out.println(integrationDto.getName());
            return integrationRepository.save(integrationDto.toAdminEntity());
        }
        return integrationRepository.save(integrationDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<IntegrationEntity> integrationEntityOptional = integrationRepository.findByName(username);
        IntegrationEntity integrationEntity = integrationEntityOptional.get();

        if (integrationEntity == null) throw new UsernameNotFoundException(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(integrationEntity.getRoleKey()));

        return new CustomIntegrationDto(integrationEntity, integrationEntity.getName(), integrationEntity.getUpwd(), authorities);
    }

    @Transactional
    public Long getMemberInfo(String username) {
        Optional<IntegrationEntity> integrationEntityOptional =  integrationRepository.findByName(username);
        IntegrationEntity integrationEntity = integrationEntityOptional.get();

        System.out.println(integrationEntity.getId());
        return integrationEntity.getId();
    }
}
