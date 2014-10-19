package pl.looksok.spring.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class SampleController {

	private MessageSource messageSource;

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getHeaderText(){
		return messageSource.getMessage("hello.content", null, Locale.US);
	}

}
