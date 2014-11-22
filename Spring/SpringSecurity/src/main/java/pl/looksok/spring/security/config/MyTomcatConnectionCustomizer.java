package pl.looksok.spring.security.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

public class MyTomcatConnectionCustomizer implements TomcatConnectorCustomizer {

	private String absoluteKeystoreFile;
	private String keystorePassword;
	private String keystoreType;
	private String keystoreAlias;

	public MyTomcatConnectionCustomizer(String absoluteKeystoreFile,
			String keystorePassword, String keystoreType, String keystoreAlias) {
		this.absoluteKeystoreFile = absoluteKeystoreFile;
		this.keystorePassword = keystorePassword;
		this.keystoreType = keystoreType;
		this.keystoreAlias = keystoreAlias.toLowerCase();

	}

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
        connector.setAttribute("keystoreAlias", keystoreAlias);
        connector.setAttribute("keyPass", keystorePassword);
	}
}
