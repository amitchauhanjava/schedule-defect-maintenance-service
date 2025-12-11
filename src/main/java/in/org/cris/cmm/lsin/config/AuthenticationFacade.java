package in.org.cris.cmm.lsin.config;

import org.springframework.security.core.Authentication;


public interface AuthenticationFacade {
	CmmUserDetails getLoggedInUser();
	Authentication getAuthentication();
}

