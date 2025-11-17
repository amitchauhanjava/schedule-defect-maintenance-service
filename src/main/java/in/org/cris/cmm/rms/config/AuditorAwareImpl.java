package in.org.cris.cmm.rms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    AuthenticationFacade authenticationFacade;
    @Override
    public Optional<String> getCurrentAuditor(){
        return Optional.of(authenticationFacade.getLoggedInUser().getUser_name());
    }
}
