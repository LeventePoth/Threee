package com.threee.security;

import com.threee.User.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  private final com.threee.security.JWTUserDetailsService jwtUserDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                com.threee.security.JWTUserDetailsService jwtUserDetailsService) {
    super(authenticationManager);
    this.jwtUserDetailsService = jwtUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader(com.threee.security.SecurityConstants.HEADER_STRING);

    if (header == null || !header.startsWith(com.threee.security.SecurityConstants.TOKEN_PREFIX)) {
      response.sendError(401, "Unauthorized");
    } else {
      UsernamePasswordAuthenticationToken userPasswordAuth = getAuthenticationToken(request);
      SecurityContextHolder.getContext().setAuthentication(userPasswordAuth);
      chain.doFilter(request, response);
    }
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
    String token= request.getHeader(com.threee.security.SecurityConstants.HEADER_STRING);
    if (token == null){
      return null;
    } else {
      Claims body= Jwts.parser().setSigningKey(com.threee.security.SecurityConstants.SECRET)
          .parseClaimsJws(token.replace(com.threee.security.SecurityConstants.TOKEN_PREFIX, ""))
          .getBody();

      Users user=new Users();
      user.setEmail(body.getSubject());

      List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(
          body.get("role").toString().substring(1,body.get("role").toString().length()-1));

      com.threee.security.JWTUserDetails userDetails = new com.threee.security.JWTUserDetails(
          user.getEmail(),
          user.getPassword(),
          grantedAuthorities);

      if (user.getEmail() != null) {
        return new UsernamePasswordAuthenticationToken(
            user, null,
            userDetails.getAuthorities());
      } else {
        return null;
      }
    }
  }
}
