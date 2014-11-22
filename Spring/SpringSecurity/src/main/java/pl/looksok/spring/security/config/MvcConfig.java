package pl.looksok.spring.security.config;

import java.io.FileNotFoundException;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MvcConfig extends WebMvcAutoConfigurationAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("hello");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/greeting").setViewName("greeting");
		registry.addViewController("/unauthorized").setViewName("unauthorized");
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() throws FileNotFoundException {
	
		final String absoluteKeystoreFile = ResourceUtils.getFile("D:\\keystore\\server.p12").getAbsolutePath();
	
		final TomcatConnectorCustomizer customizer = new MyTomcatConnectionCustomizer(
				absoluteKeystoreFile, "keyPwd", "PKCS12", "keyalias"); 
	
		return new EmbeddedServletContainerCustomizer() {
	
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				if(container instanceof TomcatEmbeddedServletContainerFactory) {
					TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
					containerFactory.addConnectorCustomizers(customizer);
				}
			};
		};
	}
}
