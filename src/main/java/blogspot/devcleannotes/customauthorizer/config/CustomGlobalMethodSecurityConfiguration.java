package blogspot.devcleannotes.customauthorizer.config;

import blogspot.devcleannotes.customauthorizer.security.CustomAccessVoter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.Collections;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class CustomGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

	@Override
	protected AccessDecisionManager accessDecisionManager() {
		return new AffirmativeBased(Collections.singletonList(new CustomAccessVoter()));
	}
}
