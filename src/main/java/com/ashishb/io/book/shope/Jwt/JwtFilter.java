package com.ashishb.io.book.shope.Jwt;

import com.ashishb.io.book.shope.Security.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetails CustomUserDetails;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().startsWith("/api/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



        final String header =  request.getHeader("Authorization");
        String jwtToken = null;
        String username =null;


        if(header != null && header.startsWith("Bearer ")){

            jwtToken = header.substring(7);

            try{
                    username = jwtProvider.getUsernameFromToken(jwtToken);
            }catch (IllegalArgumentException e){
                System.out.println("unable to extract JWT token");
            }catch (ExpiredJwtException e){
                System.out.println("JWT token Expired");
            }
        }else {
            System.out.println("Jwt token Error");
        }


        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = CustomUserDetails.loadUserByUsername(username);

                if(jwtProvider.validateToken(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null,userDetails.getAuthorities()
                            );
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
        }
        filterChain.doFilter(request, response);
    }
}
