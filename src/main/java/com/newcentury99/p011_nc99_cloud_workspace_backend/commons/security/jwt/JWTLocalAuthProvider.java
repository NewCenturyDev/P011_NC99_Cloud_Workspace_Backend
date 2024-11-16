package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.base.entities.BaseUserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.auths.profiles.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JWTLocalAuthProvider implements AuthenticationProvider {
    private AppUserRepo appUserProfileRepo;
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        BaseUserProfile profile = appUserProfileRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        extracted(profile, password);
        checkVerified(profile);
        checkActivated(profile);
        checkLocked(profile);
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    private void extracted(BaseUserProfile profile, String password) {
        // Wrong Password
        if(encoder.matches(profile.getPassword(), password)) {
            throw new BadCredentialsException("ERR_MSG : Wrong password");
        }
    }

    private static void checkVerified(BaseUserProfile profile) {
        // Not Verified
        if(!profile.getVerified()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private static void checkActivated(BaseUserProfile profile) {
        // Not Active
        if(!profile.getActive()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private static void checkLocked(BaseUserProfile profile) {
        // User is Locked
        if(!profile.getLocked()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
