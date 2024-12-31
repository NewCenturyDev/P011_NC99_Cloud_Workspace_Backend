package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt;

import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.entity.UserProfile;
import com.newcentury99.p011_nc99_cloud_workspace_backend.domains.users.repo.UserRepo;
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
    private UserRepo appUserProfileRepo;
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserProfile profile = appUserProfileRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        extracted(profile, password);
        checkVerified(profile);
        checkActivated(profile);
        checkLocked(profile);
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    private void extracted(UserProfile profile, String password) {
        // Wrong Password
        if(encoder.matches(profile.getPassword(), password)) {
            throw new BadCredentialsException("ERR_MSG : Wrong password");
        }
    }

    private static void checkVerified(UserProfile profile) {
        // Not Verified
        if(!profile.getVerified()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private static void checkActivated(UserProfile profile) {
        // Not Active
        if(!profile.getActive()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private static void checkLocked(UserProfile profile) {
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
