package com.att.cmlp.common.nlp.main.java;

import org.json.JSONArray;

import com.att.cmlp.common.nlp.main.config.ClassifierClass;
import com.att.cmlp.common.nlp.main.config.LanguageName;
import com.att.cmlp.common.nlp.main.config.LibraryName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EntityExtractionService 
{

	// Class properties
	ClassifierClass classifierClass;
	String inputText;
	LanguageName language;
    LibraryName libraryName;
	
	public EntityExtractionService() {
		// needed for autowiring
	}

	//Constructor - Initialize the attributes - Input is string
	public EntityExtractionService(LibraryName libraryName,LanguageName language,ClassifierClass classifierClass, String inString) throws IOException
	{
		
		//Exception handling with input validation
		if(inString.isEmpty() || inString==null)
			throw new NullPointerException("inString should not be empty");
		if(language==null)
			throw new NullPointerException("Language should not be empty");
		if(libraryName==null)
			throw new NullPointerException("LibraryName should not be empty");
		if(classifierClass==null)
			throw new NullPointerException("ClassifierClass should not be empty");
		this.libraryName = libraryName;
		this.language=language;
		this.classifierClass=classifierClass;
		this.inputText = inString;
		}	
	
	//Constructor - Initialize the attributes - Input is string
	public EntityExtractionService(LibraryName libraryName,LanguageName language,ClassifierClass classifierClass, String inputFilePath,String inputFile) throws IOException
	{
		
			//Exception handling with input validation
			if(inputFilePath.isEmpty() || inputFilePath==null)
				throw new NullPointerException("inputFilePath should not be empty");
			if(inputFile.isEmpty() || inputFile==null)
				throw new NullPointerException("inputFile should not be empty");
			if(language==null)
				throw new NullPointerException("Language should not be empty");
			if(libraryName==null)
				throw new NullPointerException("LibraryName should not be empty");
			if(classifierClass==null)
				throw new NullPointerException("ClassifierClass should not be empty");
			this.libraryName = libraryName;
			this.language=language;
			this.classifierClass=classifierClass;
			// Validate input file path
			String path=inputFilePath + inputFile;
			
	        List<String> lines = Files.readAllLines(Paths.get(path));
	        StringBuilder sb = new StringBuilder();
	        for (String line : lines) 
	        {
	            if (sb.length() > 0) 
	            {
	                sb.append("\n");
	            }
	            sb.append(line);
	        }
	        String contents = sb.toString();
	        this.inputText = contents;

    }
		
	
	public JSONArray getEntities() 
	{
        JSONArray jr =null;
        try
        {
			if (libraryName==LibraryName.openNLP)
			{
				
			    //create a instance for location entities
				EntityExtractorusingOpenNLP inst1= new EntityExtractorusingOpenNLP(language,classifierClass,inputText);
		        jr=inst1.run();
			}
			else if (libraryName==LibraryName.standfordNLP)
			{
			    //create a instance for location entities
		        EntityExtractorusingStanfordNLP inst1= new EntityExtractorusingStanfordNLP(language,classifierClass,inputText);
		        jr=inst1.run();
			}
        }
        catch (Exception ex)
        {
            throw ex;
        }
	
        return jr;
	}
	
//    public static void main(String[] args)
//    {
//        try
//        {
//
//
//            String  inText="One year ago, several hours before cities across the United States started their annual fireworks displays, a different type of fireworks were set off at the "
//            		+ "European Center for Nuclear Research (CERN) in Switzerland. At 9:00 a.m., physicists announced to the world that they had found something they had been searching for "
//            		+ "nearly 50 years: the elusive Higgs boson. Today, on the anniversary of its discovery, are we any closer to figuring out what that particle's true identity is? The Higgs "
//            		+ "boson is popularly referred to as the God particle, perhaps because of its role in giving other particles their mass. However, it's not the boson itself that gives mass."
//            		+ " Back in 1964, Peter Higgs proposed a theory that described a universal field (similar to an electric or a magnetic field) that particles interacted with.";
            

	    //create a instance for openNLP
//          EntityExtractionService inst1= new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
//           JSONArray jr=inst1.getEntities();
//           System.out.println(jr);
           
   	    //create a instance for StandfordNLP
//           inst1= new EntityExtractionService(LibraryName.standfordNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
//            jr=inst1.getEntities();
//           System.out.println(jr);
   	    //create a instance for openNLP - File name as parameter
//            inst1= new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\","textsample.txt");
//            jr=inst1.getEntities();
//           System.out.println(jr);
   	    //create a instance for StandfordNLP
//           inst1= new EntityExtractionService(LibraryName.standfordNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\","textsample.txt");
//            jr=inst1.getEntities();
//           System.out.println(jr);
//        } 
        
//        catch (Exception ex)
//        {
//            System.out.println(ex.getMessage());
//        }


//    }

}