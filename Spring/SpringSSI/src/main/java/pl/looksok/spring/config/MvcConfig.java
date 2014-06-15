package pl.looksok.spring.config;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

public class MvcConfig extends WebMvcAutoConfigurationAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/greeting").setViewName("greeting");
	}
}
