package in.org.cris.cmm.sdms.config;

import org.springframework.security.core.Authentication;


public interface AuthenticationFacade {
	CmmUserDetails getLoggedInUser();
	Authentication getAuthentication();
}

