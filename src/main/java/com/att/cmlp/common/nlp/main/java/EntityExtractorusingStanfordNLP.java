package com.att.cmlp.common.nlp.main.java;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.Hashtable;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;

import com.att.cmlp.common.nlp.main.config.ClassifierClass;
import com.att.cmlp.common.nlp.main.config.LanguageName;

public class EntityExtractorusingStanfordNLP {
	

	// Class properties
	ClassifierClass classifierClass;
	String inputText;
	LanguageName language;
	
	public EntityExtractorusingStanfordNLP()
	{}
    
	public EntityExtractorusingStanfordNLP(LanguageName language,ClassifierClass classifierClass, String inString) 
	{
		
		this.language=language;
		this.classifierClass=classifierClass;
		this.inputText = inString;
	}	

	
    public JSONArray run()
    {
        JSONArray jarray =null;
        try
        {
    		//Set the Classification model based on the input
    		String classifierPath="";
    		switch(language)
    		{
	    		case english:
	    		{
		    		switch(classifierClass)
		    		{
		    		case class7_LocationPersonOrganizationMoneyPercentDateTime:
		    			classifierPath = Paths.get(this.getClass().getResource("../resources/english.muc.7class.distsim.crf.ser.gz").toURI()).toString();;
		    			break;
		    		case class4_LocationPersonOrganizationMisc:
		    			classifierPath = Paths.get(this.getClass().getResource("../resources/english.conll.4class.distsim.crf.ser.gz").toURI()).toString();
		    			break;
		    		case class3_LocationPersonOrganization:
		    			classifierPath = Paths.get(this.getClass().getResource("../resources/english.all.3class.distsim.crf.ser.gz").toURI()).toString();
		    			break;
		    		}
		    		break;
	    		}
	    		default:
	    			break;
	    		
    		}
 
    		CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(classifierPath);
 	
    		String classifierOutput=classifier.classifyWithInlineXML(inputText);
        	
        	//Filter the XML elements from output
        	String searchDoc=classifierOutput;
        	int lessthantagIndex=searchDoc.indexOf('<');
        	int gtthantagIndex=searchDoc.indexOf('>');
        	ArrayList<Map<String,String>> aList= new ArrayList<Map<String,String>>();
        	while(searchDoc.length() >0)
        	{
        		String ename = searchDoc.substring(lessthantagIndex+1, gtthantagIndex);
    		    lessthantagIndex=searchDoc.indexOf("</"); 
        		String word = searchDoc.substring(gtthantagIndex+1, lessthantagIndex);
        		searchDoc=searchDoc.substring((ename.length()+word.length()+gtthantagIndex+4), searchDoc.lastIndexOf('>')+1);
        		lessthantagIndex=searchDoc.indexOf('<');
            	gtthantagIndex=searchDoc.indexOf('>');	
            	
            	Map<String,String> tmpdata = new Hashtable<String, String>();
				tmpdata.put(word, ename);
				if (!aList.contains(tmpdata))
					aList.add(tmpdata);
        	}        		
        	// Convert the Entity name and content as JSON
	    	if (!aList.isEmpty())
	    	{
	        	jarray = new JSONArray(aList);
	    	}
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return jarray;
    }

}
