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
        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if(memberOptional.isPresent()){
            throw new RuntimeException("User with given email already exist"+email);
        }
        // ÜSTTE BU EMAİLLE KAYIT VAR MI KONTROLÜ YAPILIYOR
        String encodedPassword = passwordEncoder.encode(password);


        List<Role> roles = new ArrayList<>();
        addRoleAdmin(roles);
        //addRoleUser(roles);
        Member member = new Member();

        member.setRoles(roles);
        member.setPassword(encodedPassword);
        member.setEmail(email);
        return memberRepository.save(member);

    }

    private void addRoleAdmin(List<Role> roleList){ // ADMIN ROLU VERİTABANINDA YOKSA OLUŞTUR VE VERILEN ROL LİSTESİNE EKLE VARSA SADECE ROL LİSTESİNE EKLE İŞLEMİ
        Optional<Role> findRoleAdmin = roleRepository.findByAuthority("ADMIN");
        if(!findRoleAdmin.isPresent()){
            Role adminRole = new Role();
            adminRole.setAuthority("ADMIN");
            roleList.add(roleRepository.save(adminRole));
        }
        else {
            roleList.add(findRoleAdmin.get());

        }
    }

    private void addRoleUser(List<Role> roleList){ // USER ROLU VERİTABANINDA YOKSA OLUŞTUR VE VERILEN ROL LİSTESİNE EKLE VARSA SADECE ROL LİSTESİNE EKLE İŞLEMİ
        Optional<Role> findRoleUser = roleRepository.findByAuthority("USER");
        if(!findRoleUser.isPresent()){
            Role userRole = new Role();
            userRole.setAuthority("USER");
            roleList.add(roleRepository.save(userRole));
        }
        else {
            roleList.add(findRoleUser.get());

        }
    }
}
