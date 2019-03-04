package blogspot.devcleannotes.customauthorizer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    // check for the auth header
    String authHeader = httpServletRequest.getHeader("Authorization");
    if (!StringUtils.isEmpty(authHeader) && authHeader.contains("Bearer ")) {

      String rawToken = authHeader.replace("Bearer ", "");
      log.info("Raw JWT: {}", rawToken);

      // valid jwt bearer token
      Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret.getBytes("UTF-8")).parseClaimsJws(rawToken);

      // set the security context
      SecurityContextHolder.getContext()
          .setAuthentication(new CustomAuthorization((List<String>) claims.getBody().get("access")));

    } else {
      log.error("Failed to find JWT Auth in Bearer format!");
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
