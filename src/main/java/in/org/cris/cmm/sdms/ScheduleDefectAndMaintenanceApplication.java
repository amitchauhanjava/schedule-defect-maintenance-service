package in.org.cris.cmm.sdms;

import in.org.cris.cmm.sdms.config.AuthHttpRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ScheduleDefectAndMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleDefectAndMaintenanceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate template = builder.build();
		List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
		if(CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		}
		interceptors.add(new AuthHttpRequestInterceptor());
		template.setInterceptors(interceptors);
		return template;
	}
}
