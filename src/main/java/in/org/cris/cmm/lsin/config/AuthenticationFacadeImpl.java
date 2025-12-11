package in.org.cris.cmm.lsin.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    //private final Logger log = LoggerFactory.getLogger(AuthenticationFacadeImpl.class);

    @Override
    public Authentication getAuthentication() {
        
       
        //log.info("Security Context=>"+SecurityContextHolder.getContext());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // log.info()
        //log.info(authentication+"<===Authentication Details=>"+authentication.getDetails().toString());
        //authentication.getAuthorities().forEach(System.out::println);


        //JwtAuthenticationToken details = (JwtAuthenticationToken)authentication;
        //details.getToken().getClaims().entrySet().forEach(key -> log.info("in Facade==>"+key.toString()));
        //log.info("User name: "+details.getToken().getClaims().get("user_name"));
        return authentication;
    }

    @Override
    public CmmUserDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //((Jwt)authentication.getPrincipal()).getClaims().entrySet().forEach(key -> log.info("in Facade==>"+key.toString()));
        if (authentication != null && authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken details = (JwtAuthenticationToken)authentication;
			CmmUserDetails userDetails = new CmmUserDetails(details.getToken().getClaims());
            userDetails.setAuthorities(authentication.getAuthorities());
			return userDetails;
		}
        return null;
    }

    
}
