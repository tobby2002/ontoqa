package com.acmutv.ontoqa.core.lexicon;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.acmutv.ontoqa.core.lemon.model.Argument;
import com.acmutv.ontoqa.core.lemon.model.Component;
import com.acmutv.ontoqa.core.lemon.model.Frame;
import com.acmutv.ontoqa.core.lemon.model.LexicalEntry;
import com.acmutv.ontoqa.core.lemon.model.LexicalForm;
import com.acmutv.ontoqa.core.lemon.model.LexicalSense;
import com.acmutv.ontoqa.core.lemon.model.Lexicon;
import com.acmutv.ontoqa.core.lemon.model.Property;
import com.acmutv.ontoqa.core.lemon.model.PropertyValue;
import com.acmutv.ontoqa.core.lemon.model.SynArg;

/**
 * This class realizes the entry point for each Lexical Entry.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 3.0
 */
public class LEntry {

	private static String uri;
	private static String writtenRep;
	private static List<LSynBehavior> synBehaviors;
	private static String partOfSpeech;
	private static String canonicalForm;
	private static List<LSense> senses;
    private static List<LOtherForm> otherForms;
	private static boolean decomposition;
	private static List<LexicalEntry> component;


	
	
	public LEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void setUri(LexicalEntry entry){
		uri= entry.getURI().toString();
	}
	
	public static String getUri(){
		return uri;
	}
	
	public static void setWrittenRep(LexicalEntry entry){
		writtenRep= entry.getCanonicalForm().getWrittenRep().toString();
		String[] allPart=writtenRep.split("@");
		writtenRep = allPart[0];
		writtenRep = writtenRep.substring(1, writtenRep.length()-1);
	}
	
	public static String getWrittenRep(){
		return writtenRep;
	}
	
	public static void setPartOfSpeech(LexicalEntry entry){
		partOfSpeech= entry.getPropertys().toString();
		if(partOfSpeech != ""){
			 String[] allPart=partOfSpeech.split("/");
			 String[] interestPart= allPart[allPart.length-1].split("#");
			 String temp = interestPart[interestPart.length-1];
			  partOfSpeech = temp.substring(0, temp.length()-2);
		}
	}
	
	public static String getPartOfSpeech(){
		return partOfSpeech;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setSenses(LexicalEntry entry){
		
		Collection<LexicalSense> allSenses= entry.getSenses();
		Iterator it = allSenses.iterator();
		List<String> isA = new ArrayList();
		senses= new ArrayList();
		
		while(it.hasNext()){
			
			LSense oneSense = new LSense();
			LexicalSense lexSense = (LexicalSense) it.next();
			oneSense.setReference(lexSense.getReference().toString());
			oneSense.setSense(lexSense.getURI().toString());
			
			Collection<Argument> arguments = lexSense.getIsAs();
			Iterator itArgument = arguments.iterator();
			while(itArgument.hasNext()){
				Argument argument = (Argument) itArgument.next();
				isA.add(argument.getURI().toString());
			}
			Collection<Argument> argumentsSubj = lexSense.getSubjOfProps();
			Iterator itArgumentSubj = argumentsSubj.iterator();
			while(itArgumentSubj.hasNext()){
				Argument argumentSubj = (Argument) itArgumentSubj.next();
				oneSense.setSubjOfProp(argumentSubj.getURI().toString());
			}
			Collection<Argument> argumentsObj = lexSense.getObjOfProps();
			Iterator itArgumentObj = argumentsObj.iterator();
			while(itArgumentObj.hasNext()){
				Argument argumentObj = (Argument) itArgumentObj.next();
				oneSense.setObjOfProp(argumentObj.getURI().toString());
			}
			oneSense.setIsA(isA);
			senses.add(oneSense);
		}
		
	}
	
	public static List<LSense> getSenses(){
		return senses;
	}
	

	public static String getCanonicalForm() {
		return canonicalForm;
	}

	public static void setCanonicalForm(LexicalEntry entry) {
		
		canonicalForm= entry.getCanonicalForm().getURI().toString();
	}
	

	@SuppressWarnings({ "unused", "static-access", "rawtypes" })
	public static void setSynBehavior(LexicalEntry entry) {
		Collection<Frame> frames = entry.getSynBehaviors();
		Iterator itFrames= frames.iterator();
		synBehaviors= new ArrayList();
		while(itFrames.hasNext()){
			Frame frame = (Frame) itFrames.next();
			LSynBehavior synB = new LSynBehavior();
			synB.setFrame(frame.getURI().toString());
			Map<SynArg, Collection<Argument>> mapp =frame.getSynArgs();
			for ( SynArg SynArg : mapp.keySet() ) {
			    if(SynArg.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#subject")){
			        synB.setFrameSubject(true);
			    }
			    if(SynArg.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#copulativeSubject")){
			    	synB.setFrameCopulativeSubject(true);
			    }
			    if(SynArg.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#attributiveArg")){
			    	synB.setFrameAttributiveArg(true);
			    }
			    if(SynArg.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#directObject")){
			    	synB.setFrameDirectObject(true);
			    }
			    if(SynArg.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject")){
			    	synB.setFramePrepositionalObject(true);
			    }
			    if(SynArg.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#possessiveAdjunct")){
			    	synB.setFramePossessiveAdjunct(true);
			    }
			}
			synBehaviors.add(synB);
			
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setOtherForms(LexicalEntry entry){
		Collection<LexicalForm> lexForms= entry.getOtherForms();
		Iterator lexForm= lexForms.iterator();
		String writtenR;
		otherForms = new ArrayList();
		while(lexForm.hasNext()){
			LOtherForm otherForm = new LOtherForm();
			LexicalForm oneForm = (LexicalForm) lexForm.next();
			/* written Rep*/
			writtenR= oneForm.getWrittenRep().toString();
			String[] allPart=writtenR.split("@");
			writtenR = allPart[0];
			writtenR = writtenR.substring(1, writtenR.length()-1);
			otherForm.setWrittenRep(writtenR);
			
			 Map<Property, Collection<PropertyValue>> map= oneForm.getPropertys();
			 for ( Property property : map.keySet() ) {
				 
				    if(property.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#tense")){
				    	Collection<PropertyValue> propValueTense= ( Collection<PropertyValue>) map.values().iterator().next();
				    	System.out.println(propValueTense);
				    	 
					    	Iterator iTense = propValueTense.iterator();
					    	if(iTense.hasNext()){
					    		PropertyValue oneProperty= (PropertyValue) iTense.next();
						    	  String oneProp = oneProperty.toString();
						    	  String[] allPar=oneProp.split("/");
								  String[] interestPart= allPar[allPar.length-1].split("#");
								  String tense = interestPart[1];
								  otherForm.setTense(tense);
					    	}  	  
				    }
				    if(property.toString().equals("http://www.lexinfo.net/ontology/2.0/lexinfo#number")){
				    	 Collection<PropertyValue> propValueNumber= map.values().iterator().next();
				    		  Iterator num =  propValueNumber.iterator();
					    	  if(num.hasNext()){
					    		  PropertyValue oneProperty=(PropertyValue) num.next();
						    	  String oneProp = oneProperty.toString();
						    	  String[] allPar=oneProp.split("/");
								  String[] interestPart= allPar[allPar.length-1].split("#");
								  String number = interestPart[1];
								  otherForm.setTense(number);
					    		  
					    	  }
				    	  
				    	  
				    }
				
			 }
			
			otherForms.add(otherForm);
			
		}
		
	}
	

	public static List<LOtherForm> getOtherForms() {
		return otherForms;
	}

	public static List<LSynBehavior> getSynBehaviors() {
		return synBehaviors;
	}
	
	

	public static boolean isDecomposition() {
		
		return decomposition;
	}

	public static void setDecomposition(LexicalEntry entry) {
		Collection<List<Component>> components =entry.getDecompositions();
		component = new ArrayList();
		if(components !=null){
			Iterator compOne = components.iterator();
			while( compOne.hasNext()){
				List<Component> allComponent = (List<Component>) compOne.next();
			//	System.out.println(allComponent);
				Iterator comp = allComponent.iterator();
//				while(comp.hasNext()){
//					Component oneComponent= (Component) comp.next();
//					//System.out.println("One Component" +oneComponent.getElement().get);
////					LexicalEntry entryComponent= oneComponent.getElement();
////					component.add(entryComponent);
//			     }
		     } 
			decomposition= true;
		}
		else{
			decomposition= false;
		}
	
	}


	
}