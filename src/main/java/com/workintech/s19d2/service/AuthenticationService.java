package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private MemberRepository memberRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public Member register(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> optionalRole = roleRepository.findById(2L);

        Role userRole = new Role();
        if(optionalRole.isPresent()){
            userRole = roleRepository.findById(optionalRole.get().getId()).orElseThrow();
        }

        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        Member member = new Member();

        member.setRoles(roles);
        member.setPassword(encodedPassword);
        member.setEmail(email);
        return memberRepository.save(member);

    }


}
