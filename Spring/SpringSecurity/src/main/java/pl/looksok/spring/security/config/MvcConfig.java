package pl.looksok.spring.security.config;

import java.io.FileNotFoundException;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private Logger log = LoggerFactory.getLogger(this.getClass());

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
			@Value("${keystore.alias}") final String keystoreAlias) throws FileNotFoundException {

		final String absoluteKeystoreFile = ResourceUtils.getFile(keystoreFile).getAbsolutePath();
		log.info("initializing with kestore file: " + absoluteKeystoreFile);

		final TomcatConnectorCustomizer customizer = new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {
				connector.setPort(443);
				connector.setSecure(true);
				connector.setScheme("https");
				
				connector.setAttribute("keyAlias", keystoreAlias);
                connector.setAttribute("keystorePass", keystorePassword);
                connector.setAttribute("keystoreFile", absoluteKeystoreFile);
                connector.setAttribute("clientAuth", false);
                connector.setAttribute("sslProtocol", "TLS");
//                connector.setAttribute("SSLEnabled", true);

				Http11NioProtocol protocol = (Http11NioProtocol)connector.getProtocolHandler();
//				protocol.setSSLEnabled(true);
				protocol.setKeystoreFile(absoluteKeystoreFile);
				protocol.setKeystorePass(keystorePassword);
				protocol.setKeystoreType(keystoreType);
				protocol.setKeyAlias(keystoreAlias);
				
				log.info("Connector is customized: " + connector);
			}
		};

		EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer = new EmbeddedServletContainerCustomizer() {

			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {

				log.info("Customizing container...");
				if(container instanceof TomcatEmbeddedServletContainerFactory) {

					TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
					containerFactory.addConnectorCustomizers(customizer);
					
					log.info("Tomcat container is customized: " + container);

				}
			};
		};
		return embeddedServletContainerCustomizer;
	}
}

	//        return new EmbeddedServletContainerCustomizer() {
	//            @Override
	//            public void customize(  ConfigurableEmbeddedServletContainer factory) {
	//                if (factory instanceof TomcatEmbeddedServletContainerFactory) {
	//                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
	//                    containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
	//                        @Override
	//                        public void customize(Connector connector) {
	//                            connector.setPort(Integer.parseInt(env.getProperty("server.settings.port")));
	//                            connector.setDomain(env.getProperty("server.settings.address"));
	//                            connector.setSecure(true);
	//                            connector.setScheme("https");
	//                            Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
	//                            proto.setSSLEnabled(true);
	//                            proto.setKeystoreFile(absoluteKeystoreFile);
	//                            proto.setKeystorePass(env.getProperty("security.settings.keystore.pass"));
	//                            proto.setKeystoreType(env.getProperty("security.settings.keystore.type"));
	//                            proto.setKeyAlias(env.getProperty("security.settings.key.alias"));
	//                        }
	//                    });
	//                }
	//            }
	//        };
	//    }

	//	@Bean
	//	//	@Inject
	//	public EmbeddedServletContainerCustomizer containerCustomizer(
	//			@Value("${keystore.file}") final String keystoreFile,
	//			@Value("${keystore.password}") final String keystorePassword,
	//			@Value("${keystore.type}") final String keystoreType,
	//			@Value("${keystore.alias}") final String keystoreAlias) throws FileNotFoundException
	//			{
	//		final String absoluteKeystoreFile = ResourceUtils.getFile(keystoreFile).getAbsolutePath();
	//		
	//		log.info("initializing with kestore file: " + absoluteKeystoreFile);
	//
	//		TomcatConnectorCustomizer tomcatConnectorCustomizer = new TomcatConnectorCustomizer() {
	//
	//			@Override
	//			public void customize(Connector connector) {
	//				connector.setSecure(true);
	//				connector.setScheme("https");
	//				connector.setAttribute("keystoreFile", absoluteKeystoreFile);
	//				connector.setAttribute("keystorePass", keystorePassword);
	//				connector.setAttribute("keystoreType", keystoreType);
	//				connector.setAttribute("keyAlias", keystoreAlias);
	//				connector.setAttribute("clientAuth", false);
	//				connector.setAttribute("sslProtocol", "TLS");
	//				connector.setAttribute("SSLEnabled", true);
	//				
	//				log.info("Connector is customized: " + connector);
	//			}
	//		};
	//
	//
	//		TomcatEmbeddedServletContainerFactory containerFactory = new TomcatEmbeddedServletContainerFactory();
	//		containerFactory.addConnectorCustomizers(tomcatConnectorCustomizer);
	//		
	//		EmbeddedServletContainerFactory factory;
	//		
	//		containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
	//		
	////		ConfigurableEmbeddedServletContainerFactory factory;
	//		return containerFactory;
	//		
	//		
	//		//		return (ConfigurableEmbeddedServletContainerFactory factory) -> {
	//		//			TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
	//		//
	//		//			containerFactory.addConnectorCustomizers((TomcatConnectorCustomizer) (Connector connector) -> {
	//		//				connector.setSecure(true);
	//		//				connector.setScheme("https");
	//		//				connector.setAttribute("keystoreFile", absoluteKeystoreFile);
	//		//				connector.setAttribute("keystorePass", keystorePassword);
	//		//				connector.setAttribute("keystoreType", keystoreType);
	//		//				connector.setAttribute("keyAlias", keystoreAlias);
	//		//				connector.setAttribute("clientAuth", "false");
	//		//				connector.setAttribute("sslProtocol", "TLS");
	//		//				connector.setAttribute("SSLEnabled", true);
	//		//			});
	//		//		};
	//			}
