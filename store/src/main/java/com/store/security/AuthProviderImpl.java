package com.store.security;

import com.store.model.User;
import com.store.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Value
@AllArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {

    UserRepo userRepo;

    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email);
        if(user == null) {
            user = userRepo.findByLogin(email);
        }
        if(user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        String password = authentication.getCredentials().toString();
        if(!user.getRole().equals("ADMIN")) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Неверный пароль");
            }
        } else {
            if(!user.getPassword().equals(password)) {
                throw new BadCredentialsException("Неверный пароль");
            }
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new UsernamePasswordAuthenticationToken(user,null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
