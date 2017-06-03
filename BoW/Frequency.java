package epipog.bow;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

// Class Definition for Word Frequencies
public class Frequency {
	// Constructor
	public Frequency() { }
	
	int nWords = 0;															// total number of words
	int nDocs  = 0;															// total number of documents
	HashMap<String, Integer> dict = new HashMap<String, Integer>( 10000 );	// dictionary
	
	// Add (Update) word to dictionary
	public void add( String word ) {
		// new word
		if ( !dict.containsKey( word ) )
			dict.put( word, 1 );
		// existing word, increment count
		else
			dict.put( word, dict.get( word ) + 1 );
		
		nWords++;
	}
	
	// Increment the number of documents (e.g., field in dataset) processed
	public void incrDoc() {
		nDocs++;
	}
	
	// Get the value (frequency count) for a word
	public int get( String word ) {
		return dict.get( word );
	}
	
	// Get the value (word percentage) for a word
	public float getWP( String word ) {
		return dict.get( word ) / (float) nWords;
	}
	
	// Get the value (document percentage) for a word
	public float getDP( String word ) {
		return dict.get( word ) / (float) nDocs;
	}
	
	// List the frequency count for all words in the dictionary
	public void list() {
		 //TreeMap<String, Integer> sorted = new TreeMap<>( dict );
		 HashMap<String, Integer> sorted = sortByValues( dict );  
		 for ( String key : sorted.keySet() ) {
			 int count = sorted.get( key );
			 processor.Count( key, count, count / (float) nWords, count / (float) nDocs );
		 }
	}
	
	  private static HashMap sortByValues(HashMap map) { 
		   List list = new LinkedList(map.entrySet());
		   // Defined Custom Comparator here
		   Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
				   return ((Comparable) ((Entry) (o2)).getValue())
					  .compareTo(((Entry) (o1)).getValue());
				}
		   });

		   // Here I am copying the sorted list in HashMap
		   // using LinkedHashMap to preserve the insertion order
		   HashMap sortedHashMap = new LinkedHashMap();
		   for (Iterator it = list.iterator(); it.hasNext();) {
				  Entry entry = (Entry) it.next();
				  sortedHashMap.put(entry.getKey(), entry.getValue());
		   } 
		   return sortedHashMap;
	  }

	// Interface for importing a function pointer to receive frequency counts
	public interface IFunction {
		public void Count( String word, int count, float wfreq, float dfreq );
	}
	
	public IFunction processor;	// imported function
	// Setter for imported function
	public void Processor( IFunction processor ) {
		this.processor = processor;
	}
}