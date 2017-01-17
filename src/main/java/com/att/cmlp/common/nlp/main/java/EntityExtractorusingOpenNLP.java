package com.att.cmlp.common.nlp.main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;


import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;

import com.att.cmlp.common.nlp.main.config.ClassifierClass;
import com.att.cmlp.common.nlp.main.config.LanguageName;
public class EntityExtractorusingOpenNLP 
{

	// Class properties
	ClassifierClass classifierClass;
	String inputText;
	LanguageName language;


    public EntityExtractorusingOpenNLP()
    {
    }

    public EntityExtractorusingOpenNLP(LanguageName language,ClassifierClass classifierClass, String inString) 
	{
		this.language=language;
		this.classifierClass=classifierClass;
		this.inputText = inString;
	}	

    public JSONArray run()
    {
        JSONArray jarray =null;
        ClassLoader classLoader = this.getClass().getClassLoader();  
//        try
        {
            TokenizerModel tm = null;
            if (tm == null)
            {
            	
     		   // Getting resource(File) from class loader  
     		   //File configFile=new File(classLoader.getResource("en-token.bin").getFile());  

     		// always start with a model, a model is learned from training data
     		    //Word tokenizer to split the words.
//                InputStream stream = this.getClass().getResourceAsStream("en-token.bin");
                try {
                	InputStream stream = classLoader.getResourceAsStream("en-token.bin");
            		tm = new TokenizerModel(stream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
            }

            TokenizerME wordBreaker=new TokenizerME(tm);
            TokenNameFinderModel[] findModel = new TokenNameFinderModel[3];
            
	    	String tokens[] = wordBreaker.tokenize(inputText);
    		switch(language)
    		{
	    		case english:
	    		{
		    		switch(classifierClass)
		    		{
			    		case class7_LocationPersonOrganizationMoneyPercentDateTime:
			    		case class4_LocationPersonOrganizationMisc:
			    		{
			    			findModel = new TokenNameFinderModel[7];
			    			try{
			    				findModel[0] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-location.bin"));
			    			
			                findModel[1] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-date.bin"));
			                findModel[2] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-money.bin"));
			                findModel[3] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-organization.bin"));
			                findModel[4] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-percentage.bin"));
			                findModel[5] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-person.bin"));
			                findModel[6] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-time.bin"));
			                break;
			    			}catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		}
	
			    		case class3_LocationPersonOrganization:
			    		{
			                try{
			                	findModel[0] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-location.bin"));
			                
			                findModel[1] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-person.bin"));
			                findModel[2] = new TokenNameFinderModel(classLoader.getResourceAsStream("en-ner-organization.bin"));
			    			break;
			                }catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    		}
		    		}
	    		}
	    		default:
	    			break;
    		}



            ArrayList<Map<String,String>> aList= new ArrayList<Map<String,String>>();

	    	for (int j=0;j<findModel.length;j++)
            {
	    		NameFinderME myentityFinder= new NameFinderME(findModel[j]);
		    	Span nameSpans[] = myentityFinder.find(tokens);
		    	
		    	
		    	for(Span s:nameSpans)
		    	{
		    		 int start = s.getStart();
		    		 int end = s.getEnd();
	
		    		 String Name = "";
		    		 
		    		 for (int i = start; i < end; i++) 
		    		 {
		    			   Name =  Name.length()>0? Name + " "+ tokens[i]:tokens[i];
		    		 }
		    		  
					Map<String,String> tmpdata = new Hashtable<String, String>();
					String ename=s.getType().toString().toUpperCase().trim();

					if (classifierClass.equals("4class_LocationPersonOrganizationMisc") && ename.matches("MONEY|PERCENT|DATE|TIME"))
						ename="MISC";
					tmpdata.put(Name, ename);
	
					if (!aList.contains(tmpdata))
					   aList.add(tmpdata);
	
		    	}
            }
	    	
	    	if (!aList.isEmpty())
	    	{
	        	jarray = new JSONArray(aList);
	        	return jarray;
	    	}
        } 
//        catch (Exception ex)
//        {
//            System.out.println(ex.getMessage());
//        }
		return jarray;
    }

}