package in.org.cris.cmm.rms.config;

import org.springframework.security.core.Authentication;


public interface AuthenticationFacade {
	CmmUserDetails getLoggedInUser();
	Authentication getAuthentication();
}

