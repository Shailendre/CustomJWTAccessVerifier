package blogspot.devcleannotes.customauthorizer.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.List;

@Getter
public class CustomAuthorization extends UsernamePasswordAuthenticationToken {

  private List<String> access;

  public CustomAuthorization(List<String> access) {
    super("key", "principal", Collections.emptyList());
    this.access = access;
  }
}
