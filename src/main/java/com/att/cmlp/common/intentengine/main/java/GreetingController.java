package com.att.cmlp.common.intentengine.main.java;

import java.io.IOException;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.cmlp.common.nlp.main.config.ClassifierClass;
import com.att.cmlp.common.nlp.main.config.LanguageName;
import com.att.cmlp.common.nlp.main.config.LibraryName;
import com.att.cmlp.common.nlp.main.java.EntityExtractionService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestController
public class GreetingController {

	
    @PostMapping(value="/extract_entities")
    public ResponseEntity<String> greeting(@RequestBody Greeting text) {
    	
    	EntityExtractionService inst1 = new EntityExtractionService();
		try {
			inst1 = new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,text.getContent());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	JSONArray jr=inst1.getEntities();
    	System.out.println(jr);
    	IntentExtractor extract =new IntentExtractor();
    	try {
			extract.SentenceDetect(text.getContent());
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			extract.POSTag(text.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<>(jr.toString(),HttpStatus.OK);
//    	  String modelFile = "/Users/harikrishna_gurram/study1/OpenNLP/apache-opennlp-1.6.0/bin/models/cat_train.bin";
//    	  String inputFile = "/Users/harikrishna_gurram/study1/OpenNLP/apache-opennlp-1.6.0/bin/models/training_data.txt";
//    	  ClassLoader classLoader = this.getClass().getClassLoader();  
//		   // Getting resource(File) from class loader  
//		   File configFile=new File(classLoader.getResource("training_data.txt").getFile());  
//    	  CategoryTrainUtil cat = new CategoryTrainUtil();
//    	  try {
//			cat.trainModel(inputFile, modelFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	  
//    	
//    	  CategoryDetectorUtil detector = new CategoryDetectorUtil(modelFile);
//
//    	  System.out.println(detector.getCategory("read books"));
//        return new Greeting(text.getId(),text.getContent());
    	
    }
}
