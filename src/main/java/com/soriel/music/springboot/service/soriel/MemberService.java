package com.soriel.music.springboot.service.soriel;

import com.soriel.music.springboot.domain.soriel.MemberEntity;
import com.soriel.music.springboot.domain.soriel.MemberRepository;
import com.soriel.music.springboot.web.dto.soriels.CustomMemberDto;
import com.soriel.music.springboot.web.dto.soriels.MemberDto;
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

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setUpwd(passwordEncoder.encode(memberDto.getUpwd()));


        return memberRepository.save(memberDto.toEntity()).getId();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByName(username);
        MemberEntity memberEntity = memberEntityOptional.get();

        if (memberEntity == null) throw new UsernameNotFoundException(username);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberEntity.getRoleKey()));

        return new CustomMemberDto(memberEntity, memberEntity.getName(), memberEntity.getUpwd(), authorities);
    }
}
