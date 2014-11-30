package pl.looksok.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManifestController {

//	@RequestMapping(value = "/offline.manifest")
	public String offMainifest() {

		StringBuilder strManiFstBuilder = new StringBuilder();
		strManiFstBuilder.append("CACHE MANIFEST\n");
		strManiFstBuilder.append("#v1\n");
//
		strManiFstBuilder.append("CACHE:\n");
		strManiFstBuilder.append("/err.html\n");
		strManiFstBuilder.append("/greeting\n");

//		strManiFstBuilder.append("NETWORK:");
//		strManiFstBuilder.append("*");

		strManiFstBuilder.append("FALLBACK:\n");
		strManiFstBuilder.append("/ /err.html\n"); 

		System.out.println("\n---------------\nGetting offline.MANIFEST file!\n--------\n");
		return strManiFstBuilder.toString();
	}
}
