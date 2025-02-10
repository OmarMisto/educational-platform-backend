package com.spu.OEP.SecurityConfig;

import com.spu.OEP.model.User;
import com.spu.OEP.service.AccountManagementService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    ApplicationContext context;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException
    {
     //Bearer token
        if(request.getRequestURI().equals("/session/management/login")||
                request.getRequestURI().equals("/session/management/signup")){
            filterChain.doFilter(request,response);
            return;
        }
        String authHeader=request.getHeader("Authorization");
        String token = null;
        String userEmail=null;
        if(authHeader==null||!authHeader.startsWith("Bearer ")){
            System.out.println("invalid token");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        token=authHeader.substring(7);
        try {
            userEmail=jwtService.extractUserEmail(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }
        if(userEmail!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            User user=context.getBean(AccountManagementService.class).loadUserByEmail(userEmail);
            if(jwtService.validateToken(token,user)){
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
