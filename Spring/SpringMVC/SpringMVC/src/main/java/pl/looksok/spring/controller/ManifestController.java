package pl.looksok.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManifestController {

//	@RequestMapping(value = "/offline.manifest")
	public String offMainifest() {

		StringBuilder strManiFstBuilder = new StringBuilder();
		strManiFstBuilder.append("CACHE MANIFEST\n");
//		strManiFstBuilder.append("#v1");
//
		strManiFstBuilder.append("CACHE:\n");
		strManiFstBuilder.append("/offline.html\n");

//		strManiFstBuilder.append("NETWORK:");
//		strManiFstBuilder.append("*");

		strManiFstBuilder.append("FALLBACK:\n");
		strManiFstBuilder.append("/ /offline.html\n"); 

		System.out.println("\n\n\n\n---------------\nGetting offline.MANIFEST file!\n--------\n\n\n");
		return strManiFstBuilder.toString();
	}
	
	@RequestMapping(value = "/offline", produces = "text/html")
	public String offlineHtml() {
		System.out.println("\n\n\n\n---------------\nGetting offline.html file!\n--------\n\n\n");
		return "offline";
	}
}
