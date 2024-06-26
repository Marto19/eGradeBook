package com.egradebook.eGradeBook.config;

import com.egradebook.eGradeBook.DTOs.user.AuthUserDTO;
import com.egradebook.eGradeBook.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        AuthUserDTO authUserDTO = userService.findByEmail(authentication.getName());

        request.getSession().setAttribute("userId", authUserDTO.getId());

        for (GrantedAuthority authority : authorities) {
            switch (authority.getAuthority()) {
                case "admin":
                    response.sendRedirect("/view/admin");
                    return;
                case "teacher":
                    response.sendRedirect("/teacher/" + authUserDTO.getId() + "/students");
                    return;
                case "student":
                    response.sendRedirect("/view/student");
                    return;
                case "parent":
                    response.sendRedirect("/parent/"+ authUserDTO.getId() +"/students");
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
