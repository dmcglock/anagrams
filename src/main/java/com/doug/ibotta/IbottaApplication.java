package com.doug.ibotta;

import com.doug.ibotta.words.service.DictionaryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IbottaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(IbottaApplication.class, args);

		//Preload dictionary with existing dictionary
		applicationContext.getBean(DictionaryService.class).addPredefinedDictionary();
	}
}
