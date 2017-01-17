package com.att.cmlp.common.nlp.test;

import static org.junit.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.att.cmlp.common.nlp.main.config.ClassifierClass;
import com.att.cmlp.common.nlp.main.config.LanguageName;
import com.att.cmlp.common.nlp.main.config.LibraryName;
import com.att.cmlp.common.nlp.main.java.EntityExtractionService;

public class testcaseEntityExtractor {

	@Test //InString Empty Validation
	public void testEntityExtract_InStringParamEmptyValidation() 
	{
        String  inText="";
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//InString Null Validation
	public void testEntityExtract_InStringParamNullValidation() 
	{
        String  inText=null;
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}

	@Test//LibraryName null Validation
	public void testEntityExtract_LibararyNameNullValidation() 
	{
        String  inText="Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
        		+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
            new EntityExtractionService(null,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
        	} 
         catch (Throwable ex) 
         	{
    	    e = ex;
    	    System.out.println(ex.getMessage());
         	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//LanguageName null Validation
	public void testEntityExtract_LanguageNameNullValidation() 
	{
        String  inText="Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
        		+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,null,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//ClassifierClass null Validation
	public void testEntityExtract_ClassifierClassNullValidation() 
	{
        String  inText="Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
        		+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,null,inText);
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}

	//Method to validate the given string is JSON
	public boolean isJSONValid(String test) 
	{
	    try {
	        new JSONObject(test);
	    	} 
	    catch (JSONException ex) 
	    {
	        try 
	        {
	            new JSONArray(test);
	        } 
	        catch (JSONException ex1) 
	        {
	            return false;
	        }
	    }
	    return true;
	}
	
	@Test//ClassifierClass null Validation
	public void testEntityExtract_OutputValidation() 
	{
        String  inText="Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
        		+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";
        
        JSONArray jr=null;
        try {
    	   //create a instance for openNLP
           EntityExtractionService inst1= new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,inText);
           jr=inst1.getEntities();
        	} 
         catch (Throwable ex) 
        	{
    	      
        	}

    	  assertTrue(isJSONValid(jr.toString()));
		
	}

	@Test //inputFilePath Empty Validation
	public void testEntityExtractUsingInputFile_inputFilePathParamEmptyValidation() 
	{
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"","textsample.txt");
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//inputFilePath Null Validation
	public void testEntityExtractUsingInputFile_inputFilePathParamNullValidation() 
	{
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,null,"textsample.txt");
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
    	 	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test //inputFile Empty Validation
	public void testEntityExtractUsingInputFile_inputFileParamEmptyValidation() 
	{
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\","");
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//inputFile Null Validation
	public void testEntityExtractUsingInputFile_inputFileParamNullValidation() 
	{
        
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\",null);
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
	}
	@Test//LibraryName null Validation
	public void testEntityExtractUsingInputFile_LibararyNameNullValidation() 
	{
        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(null,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\","textsample.txt");
        	} 
         catch (Throwable ex) 
         	{
    	    e = ex;
    	    System.out.println(ex.getMessage());
         	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//LanguageName null Validation
	public void testEntityExtractUsingInputFile_LanguageNameNullValidation() 
	{

        Throwable e = null;
        try {
    	   //create a instance for openNLP
          new EntityExtractionService(LibraryName.openNLP,null,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\","textsample.txt");
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//ClassifierClass null Validation
	public void testEntityExtractUsingInputFile_ClassifierClassNullValidation() 
	{

        Throwable e = null;
        try {
    	   //create a instance for openNLP
           new EntityExtractionService(LibraryName.openNLP,LanguageName.english,null,"C:\\Works\\","textsample.txt");
        	} 
         catch (Throwable ex) 
        	{
    	    e = ex;
        	}

    	  assertTrue(e instanceof NullPointerException );
		
	}
	@Test//ClassifierClass null Validation
	public void testEntityExtractUsingInputFile_OutputValidation() 
	{

        JSONArray jr=null;
        try {
    	   //create a instance for openNLP
           EntityExtractionService inst1= new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,"C:\\Works\\","textsample.txt");
           jr=inst1.getEntities();
        	} 
         catch (Throwable ex) 
        	{
        	}

    	  assertTrue(isJSONValid(jr.toString()));
		
	}
	}
