package blogspot.devcleannotes.customauthorizer.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAccessVoter implements AccessDecisionVoter<Object> {

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class aClass) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {

		// this authentication is taken from authentication context
		if (authentication instanceof CustomAuthorization) {
			List<String> roles = ((CustomAuthorization) authentication).getAccess();
			List<String> configAttribute = collection.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
			if (roles.stream().anyMatch(configAttribute::contains)) {
				return ACCESS_GRANTED;
			}else return ACCESS_DENIED;
		}
		else return ACCESS_DENIED;
	}
}
