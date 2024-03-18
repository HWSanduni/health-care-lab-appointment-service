package com.health.care.lab.appointment.config;

import com.health.care.lab.appointment.dto.UserDto;
import com.health.care.lab.appointment.repository.UserRepository;
import com.health.care.lab.appointment.service.UserService;
import com.health.care.lab.appointment.service.impl.AutUserDetailsImpl;
import com.health.care.lab.appointment.service.impl.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JWTServiceImpl JWTServiceImpl;
  @Autowired
  private UserService userService;
  @Autowired
  AutUserDetailsImpl autUserDetails;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    String jwtToken = null;
    final String userName=null;

    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }

    jwtToken = authHeader.substring(7);
    JWTServiceImpl.extractUserName(jwtToken);
    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDto userDto = userService.findByUser(userName);
      UserDetails userDetails = new User(userDto.getUserName(),userDto.getPassword(),new ArrayList<>());
      if (JWTServiceImpl.isTokenValid(jwtToken,userDetails)) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)
        );
        securityContext.setAuthentication(authToken);
        SecurityContextHolder.setContext(securityContext);
      }
    }
    filterChain.doFilter(request, response);

  }
}

