/**
 * 
 */
package com.research.innovate.microservice.template;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  09/14/2018
 * @version 1.0
 *
 * Template generic microservice service to avoid repitition of same code and to manage common
 * behavior to be inherited.
 *
 */

@SpringBootApplication
public abstract class ServiceApplication extends SpringBootServletInitializer {

	public static void main(String... args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServiceApplication.class);
    }
	
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

	    return new RepositoryRestConfigurerAdapter() {

	        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	            config.setRepositoryDetectionStrategy(
	                    RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
	        }
	    };
	}	
	
	@Bean
	abstract CommandLineRunner init(FlowersOrderMongoRepository repository){

	}

}
