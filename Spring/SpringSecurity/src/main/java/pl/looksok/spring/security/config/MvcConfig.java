package pl.looksok.spring.security.config;

import java.io.FileNotFoundException;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
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
	public EmbeddedServletContainerCustomizer containerCustomizer(
			@Value("${keystore.file}") final String keystoreFile,
			@Value("${keystore.password}") final String keystorePassword,
			@Value("${keystore.type}") final String keystoreType,
			@Value("${keystore.alias}") final String keystoreAlias
			) throws FileNotFoundException {

		final String absoluteKeystoreFile = ResourceUtils.getFile(keystoreFile).getAbsolutePath();

		final TomcatConnectorCustomizer customizer = new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {
				connector.setPort(443);
				connector.setSecure(true);
				connector.setScheme("https");
				
				connector.setAttribute("SSLEnabled", true);
                connector.setAttribute("sslProtocol", "TLS");
                connector.setAttribute("protocol", "org.apache.coyote.http11.Http11Protocol");
                connector.setAttribute("clientAuth", false);
                connector.setAttribute("keystoreFile", absoluteKeystoreFile);
                connector.setAttribute("keystoreType", keystoreType);
                connector.setAttribute("keystorePass", keystorePassword);
                connector.setAttribute("keyPass", keystorePassword);
			}
		};

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
