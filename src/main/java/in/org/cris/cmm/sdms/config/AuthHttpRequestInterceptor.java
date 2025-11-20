package in.org.cris.cmm.sdms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

public class AuthHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private final Logger LOGGER = LoggerFactory.getLogger(AuthHttpRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		request.getHeaders().add("Authorization", ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"));
		System.out.println("request.getHeaders().get(\"Authorization\")"+request.getHeaders().get("Authorization"));
		return execution.execute(request, body);
	}

}
