/**
 * 
 */
package com.research.innovate.microservice.template

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created 09/14/2018
 * @version 1.0
 *
 * Template generic microservice controller to avoid repitition of same code and to manage common
 * behavior to be inherited. 
 */

@RefreshScope
@RestController
public abstract class ServiceController {

    @Autowired
    RestTemplate restTemplate;	
    
	@Autowired
	private HealthIndicatorService healthIndicatorService;

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Value("${config.oauth2.resourceURI}")
    private String resourceURI;	    
	
    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }	
	
	@RequestMapping(value = "/health",  method = RequestMethod.GET)
	public InstanceStatus health() {
		return healthIndicatorService.health();
	}
    
    @RequestMapping("/")
    public JsonNode home() {
        return restTemplate.getForObject(resourceURI, JsonNode.class);
    }        
 }


