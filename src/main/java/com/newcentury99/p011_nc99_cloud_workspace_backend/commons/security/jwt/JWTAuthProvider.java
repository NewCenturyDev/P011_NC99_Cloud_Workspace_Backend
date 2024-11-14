package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.jwt;

import com.newcentury99.p011_nc99_cloud_workspace_backend.commons.security.entities.CommonUserProfileImpl;
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
public class JWTAuthProvider implements AuthenticationProvider {
//    private UserProfileRepo repo;
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        CommonUserProfileImpl profile = userProfileRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        extracted(profile, password);
        checkVerified(profile);
        checkLocked(profile);
        checkActivated(profile);
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    private static void checkActivated(CommonUserProfileImpl profile) {
        // Not Active
        if(!profile.getActive()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private static void checkLocked(CommonUserProfileImpl profile) {
        // User is Locked
        if(!profile.getLocked()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private static void checkVerified(CommonUserProfileImpl profile) {
        // Not Verified
        if(!profile.getVerified()) {
            throw new BadCredentialsException("ERR_MSG");
        }
    }

    private void extracted(CommonUserProfileImpl profile, String password) {
        // Wrong Password
        if(encoder.matches(profile.getPassword(), password)) {
            throw new BadCredentialsException("ERR_MSG : Wrong password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
