package in.org.cris.cmm.rms.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Override
    public CmmUserDetails getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication==null || authentication instanceof AnonymousAuthenticationToken)) {
			OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
			Map<String, Object> details = (Map<String, Object>) oauthDetails.getDecodedDetails();
			CmmUserDetails userDetails = new CmmUserDetails(details);
			return userDetails;
		}
        return null;
    }
	
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public OAuth2AuthenticationDetails getOAuth2AuthenticationDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication==null || authentication instanceof AnonymousAuthenticationToken)) {
			OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
			return oauthDetails;
		}
		return null;
	}

}
