package ru.epkkc.spring_mvc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.epkkc.spring_mvc.model.RolesEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoginSuccessHandler  implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)
                                        throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("authorities");
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
        }
        List<String> stringAuthorities = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if (stringAuthorities.contains(RolesEnum.ADMIN.name())){
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/user");
        }
//        for (GrantedAuthority authority : authorities) {
//            if (authority.getAuthority().equals("ADMIN")){
//                httpServletResponse.sendRedirect("/admin");
//            }
//        }
//        httpServletResponse.sendRedirect("/user");
    }
}
