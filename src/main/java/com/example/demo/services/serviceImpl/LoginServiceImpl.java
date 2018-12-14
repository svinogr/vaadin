package com.example.demo.services.serviceImpl;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails getAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal;
    }

    @Override
    public EnumRole login(String name, String password, boolean isAdmin) {
        EnumRole role = EnumRole.ROLE_GUEST;

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name,
                password);
        try {
            Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);

            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

            for (GrantedAuthority ga : authorities) {
                if (ga.getAuthority().equals(EnumRole.ROLE_ADMIN.name()) && isAdmin) {
                    role = EnumRole.ROLE_ADMIN;
                } else if (ga.getAuthority().equals(EnumRole.ROLE_USER.name()) || ga.getAuthority().equals(EnumRole.ROLE_ADMIN.name())) {
                    role = EnumRole.ROLE_USER;
                }
            }
            System.out.println(role +"-"+isAdmin);

            return role;
        } catch (BadCredentialsException e) {
            return role;
        }

    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }
}
