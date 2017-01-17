package com.att.cmlp.common.intentengine.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class IntentExtractor {
	public void SentenceDetect(String paragraph) throws InvalidFormatException,
	IOException {
		//String paragraph = "Hi. How are you? This is Mike.";
		ClassLoader classLoader = this.getClass().getClassLoader();  
		   // Getting resource(File) from class loader  
		   File configFile=new File(classLoader.getResource("en-sent.bin").getFile());  

		// always start with a model, a model is learned from training data
		InputStream is = new FileInputStream(configFile);
		SentenceModel model = new SentenceModel(is);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		
		String sentences[] = sdetector.sentDetect(paragraph);
		System.out.println(sentences);
		is.close();
}
//	public void findName() throws IOException {
//		InputStream is = new FileInputStream("en-ner-person.bin");
//	 
//		TokenNameFinderModel model = new TokenNameFinderModel(is);
//		is.close();
//	 
//		NameFinderME nameFinder = new NameFinderME(model);
//	 
//		String []sentence = new String[]{
//			    "Mike",
//			    "Smith",
//			    "is",
//			    "a",
//			    "good",
//			    "person"
//			    };
//	 
//			Span nameSpans[] = nameFinder.find(sentence);
//	 
//			for(Span s: nameSpans)
//				System.out.println(s.toString());			
//	}
	
	public void POSTag(String input) throws IOException {
		ClassLoader classLoader = this.getClass().getClassLoader();  
		   // Getting resource(File) from class loader  
		     
		POSModel model = new POSModelLoader()	
			.load(new File(classLoader.getResource("en-pos-maxent.bin").getFile()));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
	 
//		String input = "Hi. How are you? This is Mike.";
//		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));
//		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));	 
		perfMon.start();
		String line = input;
//		while ((line = lineStream.read()) != null) {
	 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
	 
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
	 
//			perfMon.incrementCounter();
//		}
		perfMon.stopAndPrintFinalResult();
	}
	
	public void chunk(String input) throws IOException {
		ClassLoader classLoaderOne = this.getClass().getClassLoader();  
		   // Getting resource(File) from class loader  
		     
		POSModel model = new POSModelLoader()	
			.load(new File(classLoaderOne.getResource("en-pos-maxent.bin").getFile()));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
	 
//		String input = "Hi. How are you? This is Mike.";
//		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(input));
	 
		perfMon.start();
		String line = input;
		String whitespaceTokenizerLine[] = null;
	 
		String[] tags = null;
//		while ((line = lineStream.read()) != null) {
			whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			tags = tagger.tag(whitespaceTokenizerLine);
	 
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
//				perfMon.incrementCounter();
//		}
		perfMon.stopAndPrintFinalResult();
		ClassLoader classLoader = this.getClass().getClassLoader();  
		   // Getting resource(File) from class loader  
		   File configFile=new File(classLoader.getResource("en-chunker.bin").getFile());  

		// chunker
		InputStream is = new FileInputStream(configFile);
		ChunkerModel cModel = new ChunkerModel(is);
	 
		ChunkerME chunkerME = new ChunkerME(cModel);
		String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
	 
		for (String s : result)
			System.out.println(s);
	 
		Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
		for (Span s : span)
			System.out.println(s.toString());
	}
}
