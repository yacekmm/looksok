package pl.looksok.spring;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Configuration
@ComponentScan({"pl.looksok.spring"})
public class Application {

	@Autowired
	private Environment env;

	private static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(Application.class);

		Environment env = app.run(args).getEnvironment();

		log.info("Application JMX demo is running! Access URLs: " +
						"Local: http://127.0.0.1:8080/api/my-jmx-value " +
						"External: http://{}:8080/api/my-jmx-value",
				InetAddress.getLocalHost().getHostAddress());

	}
}
