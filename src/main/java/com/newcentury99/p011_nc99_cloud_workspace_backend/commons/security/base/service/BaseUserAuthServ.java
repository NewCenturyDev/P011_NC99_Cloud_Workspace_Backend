package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.service;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.messages.MessageConfig;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.repo.BaseUserRepo;
import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.verifications.emails.service.EmailVerifyServ;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;

@AllArgsConstructor
@Service
public class BaseUserAuthServ implements UserDetailsService {
    private final BaseUserRepo userRepo;
    private final EmailVerifyServ emailVerifyServ;
    private final PasswordEncoder pwEncoder;
    private final MessageSource msgSrc = MessageConfig.getUserMsgSrc();


    @Override
    // 스프링 시큐리티 유저 권한 정보 로드
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        msgSrc.getMessage("error.user.notExist", null, Locale.ENGLISH))
                );
    }

    // 유저네임과 비밀번호로 스프링 시큐리티 인증
    @Transactional
    public BaseUserProfile loginByCredential(String email, String password) {
        BaseUserProfile profile = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        // 비밀번호 확인
        if(!pwEncoder.matches(password, profile.getPassword())) {
            throw new BadCredentialsException(msgSrc.getMessage("error.login.fail", null, Locale.ENGLISH));
        }

        profile.setLastLoginAt(LocalDateTime.now());
        return profile;
    }

    @Transactional
    public BaseUserProfile verifyUserEmail(String email, String code) throws Exception {
        BaseUserProfile userProfile;

        if(emailVerifyServ.checkAuthCode(email, code)) {
            userProfile = this.userRepo.findByEmail(email).orElseThrow(() -> new Exception(
                    this.msgSrc.getMessage("error.user.notExist", null, Locale.ENGLISH)
            ));
            userProfile.setVerified(true);
        } else {
            throw new Exception(this.msgSrc.getMessage(
                    "error.code.verify.denied", null, Locale.ENGLISH
            ));
        }
        return userProfile;
    }
}
