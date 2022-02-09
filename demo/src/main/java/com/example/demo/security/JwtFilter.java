package com.example.demo.security;

import com.example.demo.constant.JwtConstant;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = CookieUtil.getValue(request, JwtConstant.JWT_COOKIE_NAME);
        System.out.println("+++++++++JwtFilter++++++++");
        System.out.println(token);
        if (token!=null) {
            String userName = JwtUtil.getSubjectFromJwt(token);
            System.out.println(userName);
//            String role = null;
//            Claims claims = JwtUtil.getClaimsFromJwt(token);
//            if(claims!=null){
//                role = claims.get("role").toString();
//            }
            if (userName!=null) {
                request.setAttribute("userName", userName);
//                request.setAttribute("role",role);
                filterChain.doFilter(request, response);
            } else {
                String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
                response.sendRedirect(authLoginUrl + "?redirect=" + request.getRequestURL());
            }
        } else {
            String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
            response.sendRedirect(authLoginUrl + "?redirect=" + request.getRequestURL());
        }
    }
}
