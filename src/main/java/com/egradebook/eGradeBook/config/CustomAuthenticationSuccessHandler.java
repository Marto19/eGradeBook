package com.egradebook.eGradeBook.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            switch (authority.getAuthority()) {
                case "admin":
                    response.sendRedirect("/view/admin");
                    return;
                case "teacher":
                    response.sendRedirect("/view/teacher");
                    return;
                case "student":
                    response.sendRedirect("/view/student");
                    return;
                case "parent":
                    response.sendRedirect("/view/parent");
                    return;
                case "principal":
                    response.sendRedirect("/view/principal");
                    return;
                default:
                    break;
            }
        }

        response.sendRedirect("/default");
    }
}
