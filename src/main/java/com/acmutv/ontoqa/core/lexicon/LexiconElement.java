package com.acmutv.ontoqa.core.lexicon;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.acmutv.ontoqa.core.lemon.model.Frame;
import com.acmutv.ontoqa.core.lemon.model.LexicalForm;
import com.acmutv.ontoqa.core.lemon.model.LexicalSense;

/**
 * This class defines the lexicon element data structure.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 */
public class LexiconElement {
	
	private String name;
	private String type;
	private List<String> synBeh;
	private List<String> forms;
	private List<String> senses;
	
	
	public LexiconElement(String name, String type, List<String> synBeh, List<String> forms) {
		super();
		this.name = name;
		this.type = type;
		this.synBeh = synBeh;
		this.forms = forms;
	}

	public LexiconElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	
	@SuppressWarnings("unused")
	public void setName(String name) {
		
		  String[] allPart=name.split("/");
		  String[] interestPart= allPart[allPart.length-1].split("#");
		  String[] tupla= interestPart[1].split("__");	  
		  String type= tupla[1];
		  String nn = tupla[0];
		  String[] nameP = nn.split("\\+");
		  String nameTemp = nameP[0];
		  
		  for( int i = 1; i< nameP.length; i++){
			String p = nameP[i];
			  nameTemp += " "+ nameP[i];
		  }	  
		this.name = nameTemp;
	}
	
	public String getType() {
		return type;
	}
	
	@SuppressWarnings("rawtypes")
	public void setType(String type, Collection<URI> values) {
		
		  String lexinfo= " ";
		  if(!type.equals("[]")){
			  String[] allPart=type.split("/");
			  String[] interestPart= allPart[allPart.length-1].split("#");
			  lexinfo = interestPart[1];
			  int num= lexinfo.length() -2;
			  lexinfo = lexinfo.substring(0, num);
			  
		  }else{
			  Iterator it = values.iterator();
			  it.next();
			  if( it.hasNext()){
				  URI value = (URI) it.next();  
				  String val = value.toString();
				  String[] allPart=val.split("/");
				  String[] interestPart= allPart[allPart.length-1].split("#");
				  lexinfo = interestPart[1];	  
			  }
		  }
		this.type = lexinfo;
	}
	
	public void setTypeLexInfo(String type) {
		
		  String lexinfo= " ";
		  this.type = lexinfo;
	}
	
	
	public List<String>getSynBeh() {
		return synBeh;
	}

	@SuppressWarnings("rawtypes")
	public void setSynBeh(Collection<Frame> values) {
		  List<String> allFrame = new ArrayList<String>();
		  Iterator it = values.iterator();
		  while(it.hasNext()){
			  String lexForm = it.next().toString();
			  String[] allPart = lexForm.split("/");
			  String interestPart= allPart[allPart.length-1];
			  allFrame.add(interestPart);
		  }
		this.synBeh = allFrame;
	}

	public List<String> getForms() {
		return forms;
	}

	@SuppressWarnings("rawtypes")
	public void setForms(Collection<LexicalForm> values) {
		
		 List<String> allForms = new ArrayList<String>();
		  Iterator it = values.iterator();
		  while(it.hasNext()){
			  String lexForm = it.next().toString();
			  String[] allPart = lexForm.split("/");
			  String interestPart= allPart[allPart.length-1];
			  allForms.add(interestPart);
		  }
		this.forms = allForms;
	}
	

	public List<String> getSenses() {
		return senses;
	}

	@SuppressWarnings("rawtypes")
	public void setSenses(Collection<LexicalSense> values) {
		
		  List<String> allSense = new ArrayList<String>();
		  
		  Iterator it = values.iterator();
		  while(it.hasNext()){
			  String lexForm = it.next().toString();
			  String[] allPart = lexForm.split("/");
			  String interestPart= allPart[allPart.length-1];
			  allSense.add(interestPart);
		  }
		this.senses = allSense;
	}

	
}