package epipog.bow;

import java.util.ArrayList;

// Class Definition for Bag of Words (NLP)
//
public class BoW {
	private String sentence = null;		// processed sentence
	private String original = null;		// original sentence
	private String[] words  = null; 	// ordered list of words
	private boolean stemmed = false;	// words have been stemmed
	
	// Constructor
	public BoW( String sentence ) {
		this.sentence = sentence;
		
		// save the original sentence
		original      = sentence;
	}
	
	// Getter for the processed sentence
	//
	public String sentence() {
		return sentence;
	}
	
	// Getter for ordered list of words
	//
	public String[] words() {
		return words;
	}
	
	// Clean the sentence of extra whitespace, punctuation, double quotes and lowercase
	//
	public void clean() throws NullPointerException {
		if ( null == sentence )
			throw new NullPointerException( "No Sentence");
		
		// remove double quotes
		sentence = sentence.replaceAll( "\"", "" );

		// split apart contractions
		contractions();
		
		// replace punctuation with space
		sentence = sentence.replaceAll( "[\\W]", " " );
		
		// replace multiple spaces with single space
		sentence = sentence.replaceAll(" {2,}", " ");
		
		// lower case sentence
		sentence = sentence.toLowerCase();
		
		// tokenize the sentence
		tokenize();
	}	
	
	// Split apart contractions, remove remaining single quotes
	//
	private void contractions() { 
		sentence = sentence.replaceAll( "'ll", " will" ); 
		sentence = sentence.replaceAll( "'ve", " have" );  
		sentence = sentence.replaceAll( "'re", " are" ); 
		sentence = sentence.replaceAll( "'s ", " is " );  
		sentence = sentence.replaceAll( "can't", "can not" );  
		sentence = sentence.replaceAll( "n't", " not" );  
		sentence = sentence.replaceAll( "'d ", " would " );  
		sentence = sentence.replaceAll( "'m ", " am " ); 
		sentence = sentence.replaceAll( "\\.com", "" );  
		sentence = sentence.replaceAll( "'", "" );
	}
	
	// Tokenize the sentence into an ordered list of words
	//
	private void tokenize() {
		words = sentence.split( " " );
	}

	// Reduce Word to its Stem (root)
	public void stemming( boolean lemma ) { 
		if ( null == sentence )
			throw new NullPointerException( "No Sentence");
		if ( null == words )
			tokenize();
		
		Stemming stem = new Stemming( words );
		
		// Replace wording endings with root stems
		words = stem.replace( lemma );
		
		stemmed = true;	// set stemmed flag
	}
	
	public void stemming() { stemming( false ); }
	public void lemma() { stemming( true); }
	
	// Remove Stop Words
	//	rmFlags - lists to remove
	//  exFlags - lists to not remove (excluding from Porter)
	//  keep    - optional additional list of words to keep
	//  exclude - optional additional list of words to exclude
	public void stopWords( int rmFlags, int exFlags, String[] keep, String[] exclude ) throws NullPointerException { 
		if ( null == sentence )
			throw new NullPointerException( "No Sentence");
		if ( null == words )
			tokenize();
		
		StopWords stop = new StopWords( words );
		
		// Set removing numbers
		if ( ( rmFlags & StopWords.NUMBER ) != 0 )
			stop.removeNumber();	
		
		// Set removing short words
		if ( ( rmFlags & StopWords.SHORTWORD ) != 0 )
			stop.remove2Letter();
		
		// Set removing articles
		if ( ( rmFlags & StopWords.ARTICLE ) != 0 )
			stop.removeArticle();
		
		// Set removing demonstratives
		if ( ( rmFlags & StopWords.DEMONSTRATIVE ) != 0 )
			stop.removeDemonstrative();
		
		// Set removing conjunctions
		if ( ( rmFlags & StopWords.CONJUNCTION ) != 0 )
			stop.removeConjunction();
		
		// Set removing prepositions
		if ( ( rmFlags & StopWords.PREPOSITION ) != 0 )
			stop.removePreposition();
		
		// Set removing pronouns
		if ( ( rmFlags & StopWords.PRONOUN ) != 0 )
			stop.removePronoun();
		
		// Set removing quantifiers
		if ( ( rmFlags & StopWords.QUANTIFIER ) != 0 )
			stop.removeQuantifier();
		
		// Set removing question words (who, what, ...)
		if ( ( rmFlags & StopWords.QUESTION ) != 0 )
			stop.removeQuestion();
		
		// Set removing Porter list of Stop Words
		if ( ( rmFlags & StopWords.PORTER ) != 0 )
			stop.removePorter( exFlags );
		
		// Add additional lists of words to keep or exclude
		stop.keep( keep );
		stop.exclude( exclude );
		
		// Remove the stop words
		words = stop.remove( stemmed );
	}
	
	// Reduce to Common Word
	//
	public void reduce() {
		if ( null == sentence )
			throw new NullPointerException( "No Sentence");
		if ( null == words )
			tokenize();
		
		Reduce re = new Reduce( words );
		
		// Replace with common word
		words = re.replace();
	}
}
