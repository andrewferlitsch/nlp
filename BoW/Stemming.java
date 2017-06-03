package epipog.bow;

import java.util.ArrayList;

// Class Definition for Stemming
//
public class Stemming {
	private String[] words	  		   = null;		// original list of words
	
	// Constructor
	public Stemming( String[] words ) { 
		this.words = words;
	}
	
	// Replace word endings with their root stems (e.g., reading -> read)
	public String[] replace( boolean lemma ) {
		Lemmatization.flag = lemma;
		
		// Check each word in the list
		for ( int i = 0; i < words.length; i++ ) {
			int len = words[ i ].length();	// word length
			
			// 'ing' endings, drop ing
			if ( len > 5 && words[i].endsWith( "ing") && !words[i].endsWith( "thing" ) ) {
				// double letter added before ing (remove double letter, except double l)
				if ( words[i].charAt( len - 4 ) != 'l' && ( words[i].charAt( len - 4 ) == words[i].charAt( len - 5 ) ) )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 4 ), Lemmatization.ING );
				else
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 3 ), Lemmatization.ING );
			}
			// 'iest' endings, replace with y
			else if ( len > 6 && words[i].endsWith( "iest") ) {
				words[ i ] =Lemmatization.lemma( words[i].substring( 0, len - 4 ) + "y", Lemmatization.IEST );
			}
			// 'est' endings, drop est
			else if ( len > 5 && words[i].endsWith( "est") ) {
				// double letter added before ing (remove double letter, except double l)
				if ( words[i].charAt( len - 4 ) != 'l' && ( words[i].charAt( len - 4 ) == words[i].charAt( len - 5 ) ) )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 4 ), Lemmatization.EST );
				else
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 3 ), Lemmatization.EST );
			}
			// 'ies' plural ending, replace with y
			else if ( len > 5 && words[i].endsWith( "ies") ) {
				words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 3 ) + "y", Lemmatization.IES );
			}
			// 'ves' plural ending, replace with fe
			else if ( len > 5 && words[i].endsWith( "ves") ) {
				words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 3 ) + "fe", Lemmatization.VES );
			}
			// 'oes' plural ending, replace with o
			else if ( len > 5 && words[i].endsWith( "oes") ) {
				words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 3 ) + "o", Lemmatization.OES );
			}
			// 'us' plural ending, replace with i
			else if ( len > 4 && words[i].endsWith( "us") ) {
				// cannot be proceeded by a vowel
				if ( words[i].charAt( len - 3 ) != 'o' && words[i].charAt( len - 3 ) != 'u' && 
				     words[i].charAt( len - 3 ) != 'a' && words[i].charAt( len - 3 ) != 'e' &&
					 words[i].charAt( len - 3 ) != 'i' )
					words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 2 ) + "i", Lemmatization.US );
			}
			// 'es' (or 's') plural endings
			else if ( len > 4 && words[i].endsWith( "es") ) {
				// e before es
				if ( words[i].charAt( len - 3 ) == 'e' )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 1 ), Lemmatization.ES );
				// noun ends in -s, -ss, -sh, -ch, -x, or -z
				else if ( words[i].charAt( len - 3 ) == 's' || words[i].charAt( len - 3 ) == 'x' || words[i].charAt( len - 3 ) == 'z' ||
						  ( words[i].charAt( len - 3 ) == 'h' && ( words[i].charAt( len - 4 ) == 's' || words[i].charAt( len - 4 ) == 'c' ) ) )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 2 ), Lemmatization.ES );
				// 's' plural
				else
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 1 ), Lemmatization.S );
			}
			// 'ved' past tense, drop d
			else if ( len > 5 && words[i].endsWith( "ved") ) {
				words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 1 ), Lemmatization.VED );
			}
			// 'ied' ending, replace with y
			else if ( len > 5 && words[i].endsWith( "ied") ) {
				words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 3 ) + "y", Lemmatization.IED );
			}
			// 'ed' past tense, drop ed
			else if ( len > 4 && words[i].endsWith( "ed") ) {
				// e before ed, drop the d
				if ( words[i].charAt( len - 3 ) == 'e' )
					words[i] = Lemmatization.lemma( words[i].substring( 0, words[i].length() - 1 ), Lemmatization.ED );
				// double letter added before ing (remove double letter, except double l)
				else if ( words[i].charAt( len - 3 ) != 'l' && ( words[i].charAt( len - 3 ) == words[i].charAt( len - 4 ) ) )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 3 ), Lemmatization.ED );
				// drop ed
				else
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 2 ), Lemmatization.ED );
			}
			// 'ier' ending, replace with y
			else if ( len > 5 && words[i].endsWith( "ier") ) {
				words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 3 ) + "y", Lemmatization.IER );
			}
			// 'er' ending, drop er
			else if ( len > 4 && words[i].endsWith( "er") ) {
				// e before er, drop the r
				if ( words[i].charAt( len - 3 ) == 'e' )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 1 ), Lemmatization.ER );
				// double letter added before er (remove double letter, except double l)
				else if ( words[i].charAt( len - 3 ) != 'l' && ( words[i].charAt( len - 3 ) == words[i].charAt( len - 4 ) ) )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 3 ), Lemmatization.ER );
				// drop er
				else {
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 2 ), Lemmatization.ER );
				}
			}
			// 'ly' ending, drop ly
			else if ( len > 4 && words[i].endsWith( "ly") ) {
				// ily ending, replace with y
				if ( words[i].charAt( len - 3 ) == 'i' )
					words[ i ] = Lemmatization.lemma( words[i].substring( 0, len - 3 ) + "y", Lemmatization.LY );
				else
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 2 ), Lemmatization.LY );
			}
			// 's' ending, drop s
			else if ( len > 3 && words[i].endsWith( "s" ) ) {
				// not double ss ending or before e
				if ( words[i].charAt( len - 2 ) != 's' && words[i].charAt( len - 2 ) != 'e' )
					words[i] = Lemmatization.lemma( words[i].substring( 0, len - 1 ), Lemmatization.S );
			}
		}
		
		return words;
	}
	
	public String[] replace() {
		return replace( false );
	}
}
