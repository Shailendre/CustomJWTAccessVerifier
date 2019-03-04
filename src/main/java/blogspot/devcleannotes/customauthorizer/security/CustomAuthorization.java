package blogspot.devcleannotes.customauthorizer.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomAuthorization extends UsernamePasswordAuthenticationToken {

  private List<String> access;

  public CustomAuthorization(List<String> access) {
    super("key", "principal", new ArrayList<GrantedAuthority>());
    this.access = access;
  }
}
