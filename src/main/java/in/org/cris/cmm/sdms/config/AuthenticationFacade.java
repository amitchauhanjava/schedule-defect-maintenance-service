package in.org.cris.cmm.sdms.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public interface AuthenticationFacade {
	CmmUserDetails getLoggedInUser();
	Authentication getAuthentication();
	OAuth2AuthenticationDetails getOAuth2AuthenticationDetails();
} 
