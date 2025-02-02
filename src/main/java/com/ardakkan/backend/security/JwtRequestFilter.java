package com.ardakkan.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MyUserDetailsService userDetailsService;
    private final TokenService tokenService;

    public JwtRequestFilter(MyUserDetailsService userDetailsService, TokenService tokenService) {
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // İzin verilen rotalar için JWT doğrulamasını atla
        String requestPath = request.getServletPath();
        if (requestPath.startsWith("/login") || requestPath.startsWith("/register") || requestPath.startsWith("/homepage")|| requestPath.startsWith("/api")|| requestPath.startsWith("/logout")) {
            filterChain.doFilter(request, response);
            return; // Bu isteği daha fazla filtreleme yapmadan devam ettiriyoruz
        }

        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // "Bearer " kısmını çıkarıyoruz
            email = tokenService.extractEmail(jwt); // Token'dan email'i çıkarıyoruz
        }

        // Eğer email mevcut ve kullanıcı daha önce doğrulanmamışsa
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = this.userDetailsService.loadUserByUsername(email);

            // Token geçerli mi kontrolü
            if (tokenService.isTokenValid(jwt, userDetails.getUsername())) { // `isTokenValid` artık email kullanıyor
                var authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Kullanıcıyı doğruluyoruz
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
