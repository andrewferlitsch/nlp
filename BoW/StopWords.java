package epipog.bow;

import java.util.ArrayList;

// Class Definition for Removing Stop Words
//
public class StopWords {
	private String[] words	  		= null;		// original list of words
	private ArrayList<String> newWords = null;	// word list of words
	private boolean rmNumber  		= false;	// flag to remove numbers (as words)
	private boolean rm2letter 		= false;	// flag to remove 1 and 2 digit words
	private boolean rmArticle 		= false;	// flag to remove articles
	private boolean rmDemonstrative = false;	// flag to remove demonstratives
	private boolean rmConjunction   = false;	// flag to remove conjunctions
	private boolean rmPreposition   = false;	// flag to remove prepositions
	private boolean rmPronoun       = false;	// flag to remove pronouns
	private boolean rmQuantifier    = false;	// flag to remove quantifiers
	private boolean rmQuestion      = false;	// flag to remove question words (who, what, ...)
	private boolean rmPorter        = false;	// flag to remove porter list of words	
	
	// Stop Words Removal Flags
	public static final int NUMBER    	  = 0x1;	// remove numbers
	public static final int SHORTWORD 	  = 0x2;	// remove 1 and 2 letter words
	public static final int ARTICLE   	  = 0x4;	// remove articles
	public static final int DEMONSTRATIVE = 0x8;	// remove demonstratives
	public static final int CONJUNCTION   = 0x10;	// remove conjunctions
	public static final int PREPOSITION   = 0x20;	// remove prepositions
	public static final int PRONOUN       = 0x40;	// remove pronouns
	public static final int QUANTIFIER    = 0x80;	// remove quantifiers
	public static final int QUESTION      = 0x100;	// remove question words (what, where, ...)
	public static final int PORTER		  = 0x200;	// remove Porter list of stop words
	
	// Constructor
	public StopWords( String[] words ) {
		 this.words = words;
		 
		 // Allocate empty list of words (to return)
		 newWords = new ArrayList<String>();
	}
	
	// Setters
	public void removeNumber()  	  { rmNumber  		= true; }
	public void remove2Letter() 	  { rm2letter 		= true; }
	public void removeArticle() 	  { rmArticle 		= true; }
	public void removeDemonstrative() { rmDemonstrative = true; }
	public void removeConjunction()   { rmConjunction   = true; }
	public void removePreposition()   { rmPreposition   = true; }
	public void removePronoun()       { rmPronoun       = true; }
	public void removeQuantifier()    { rmQuantifier    = true; }
	public void removeQuestion()      { rmQuestion      = true; }
	public void removePorter( int exFlag ) { 
		rmPorter   = true;
		if ( ( exFlag & ARTICLE ) == 0 )
			rmArticle = true;
		if ( ( exFlag & DEMONSTRATIVE ) == 0 )
			rmDemonstrative = true;
		if ( ( exFlag & CONJUNCTION ) == 0 )
			rmConjunction = true;
		if ( ( exFlag & PREPOSITION ) == 0 )
			rmPreposition = true;
		if ( ( exFlag & PRONOUN ) == 0 )
			rmPronoun = true;
		if ( ( exFlag & QUANTIFIER ) == 0 )
			rmQuantifier = true; 
		if ( ( exFlag & QUESTION ) == 0 )
			rmQuestion = true; 
	}
	
	private String[] keepWords    = null;	// list of words to keep
	private String[] excludeWords = null;	// list of additional words to exclude
	
	// Words to keep
	//
	public void keep( String[] keep ) {
		keepWords = keep;
	}
	
	// Additional words to exclude
	public void exclude( String[] exclude) {
		excludeWords = exclude;
	}
	
	// Remove Stop Words
	//
	public String[] remove( boolean stemmed ) {
		// Process each word in list
		for ( String word : words ) {
			
			// empty word
			int len = word.length();
			if (  len == 0 )
				continue;
			
			// Expand common abbreviated words
			switch ( word.charAt( 0  ) ) {
			case 'a': switch ( word ) {
					  case "ave": newWords.add( "avenue"); continue;
					  case "app": newWords.add( "application"); continue;
					  case "apr": newWords.add( "april"); continue;
					  case "aug": newWords.add( "august"); continue;
					  }
					  break;
			case 'b': switch ( word ) {
					  case "blvd": newWords.add( "boulevard"); continue;
					  }
					  break;
			case 'c': switch ( word ) {
					  case "ct"  : newWords.add( "court"); continue;
					  case "ctr" : newWords.add( "center"); continue;
					  case "capt": newWords.add( "captain"); continue;
					  case "corp": newWords.add( "corporation"); continue;
					  }
					  break;
			case 'd': switch ( word ) {
					  case "doc" : newWords.add( "document"); continue;
					  case "dec" : newWords.add( "december"); continue;
					  case "div" : newWords.add( "division"); continue;
					  case "dept": newWords.add( "department"); continue;
					  case "dat" : newWords.add( "data"); continue;
					  }
					  break;
			case 'e': switch ( word ) {
					  case "est": newWords.add( "estimate"); continue;
					  case "eu" : newWords.add( "europe"); continue;
					  }
					  break;
			case 'f': switch ( word ) {
					  case "feb": newWords.add( "february" ); continue;
					  case "ft" : newWords.add( "foot" ); continue;
					  case "fri": newWords.add( "friday" ); continue;
					  case "fb": newWords.add( "facebook" ); continue;
					  }
					  break;
			case 'g': switch ( word ) {
					  case "grad": newWords.add( "graduate" ); continue;
					  }
					  break;
			case 'h': switch ( word ) {
					  case "hrs": newWords.add( "hour" ); continue;
					  }
					  break;
			case 'i': switch ( word ) {
					  case "int": newWords.add( "integer"); continue;
					  }
					  break;
			case 'j': switch ( word ) {
					  case "jan": newWords.add( "january"); continue;
					  case "jun": newWords.add( "june"); continue;
					  case "jul": newWords.add( "july"); continue;
					  }
					  break;
			case 'k': break;
			case 'l': switch ( word ) {
					  case "lb" : newWords.add( "pound" ); continue;
					  case "lbs": newWords.add( "pounds" ); continue;
					  case "ln" : newWords.add( "lane" ); continue;
					  case "luv": newWords.add( "love" ); continue;
					  case "lib": newWords.add( "library" ); continue;
					  case "lat": newWords.add( "latitude" ); continue;
					  case "lon": newWords.add( "longitude" ); continue;
					  }
					  break;
			case 'm': switch ( word ) {
					  case "min": newWords.add( "minimum" ); continue;
					  case "max": newWords.add( "maximum" ); continue;
					  case "mt" : newWords.add( "mount" ); continue;
					  case "mtn": newWords.add( "mountain" ); continue;
					  case "mar": newWords.add( "march" ); continue;
					  case "mul": newWords.add( "multiply" ); continue;
					  case "mon": newWords.add( "monday" ); continue;
					  }
					  break;
			case 'n': switch ( word ) {
					  case "ne" : newWords.add( "northeast"); continue;
					  case "nw" : newWords.add( "northwest"); continue;
					  case "nov": newWords.add( "november"); continue;
					  }
					  break;
			case 'o': switch ( word ) {
					  case "oct": newWords.add( "october"); continue;
					  case "ok" : newWords.add( "okay"); continue;
					  }
					  break;
			case 'p': switch ( word ) {
					  case "pt": newWords.add( "pint"); continue;
					  }
					  break;
			case 'q': switch ( word ) {
					  case "qt": newWords.add( "quart"); continue;
					  }
					  break;
			case 'r': switch ( word ) {
					  case "rd": newWords.add( "road"); continue;
					  }
					  break;
			case 's': switch ( word ) {
					  case "se"  : newWords.add( "southeast"); continue;
					  case "sw"  : newWords.add( "southwest"); continue;
					  case "str" : newWords.add( "string"); continue;
					  case "st"  : newWords.add( "street"); continue;
					  case "sep" : newWords.add( "september"); continue;
					  }
					  break;
			case 't': switch ( word ) {
					  case "tsp" : newWords.add( "teaspoon" ); continue;
					  case "tbsp": newWords.add( "tablespoon" ); continue;
					  case "tue" : newWords.add( "tuesday" ); continue;
					  case "thu" : newWords.add( "thursday" ); continue;
					  case "tech": newWords.add( "technical" ); continue;
					  case "tv"  : newWords.add( "television" ); continue;
					  }
					  break;
			case 'u': switch ( word ) {
					  case "u": newWords.add( "you" ); continue;
					  }
					  break;
			case 'v': switch ( word ) {
					  case "v": newWords.add( "vs" ); continue;
					  }
					  break;
			case 'w': switch ( word ) {
					  case "wed": newWords.add( "wed" ); continue;
					  }
					  break;
			case 'x': switch ( word ) {
					  case "xmas": newWords.add( "xmas" ); continue;
					  }
					  break;
			case 'y': break;
			case 'z': break;
			}
			
			// Check if word is a word to keep
			if ( null != keepWords ) {
				boolean found = false;
				for ( String keep : keepWords ) {
					if ( keep.equals( word ) ) {
						// Add the word to the new list (to return)
						newWords.add( word );
						found = true;
						break;
					}
				}
				if ( true == found ) continue;
			}
			
			// Remove one and two letter words
			if ( rm2letter == true ) {
				if ( word.length() <= 2 )
					continue;
			}
			
			// Remove numbers as words
			if ( rmNumber == true ) {
				String w;
				if ( len > 2 && word.endsWith( "th" ) )
					w = word.substring( 0, word.length() - 2 );
				else if ( len > 4 && word.endsWith( "teen" ) )
					w = word.substring( 0, word.length() - 4 );
				else
					w = word;
				
				// remove numbers as digits
				if ( w.charAt(0) >= 0 && w.charAt(0) <= '9' )
					continue;
				
				switch ( w.charAt(0) ) {
				case 'b': if ( w.equals( "billion" ) ) continue;
						  break;
				case 'e': switch ( w ) {
					      case "eight": case "eigh" /* eighth */ : continue;
						  }
						  break;
				case 'f': switch ( w ) {
					      case "first": case "fif" /* fifth */ : case "four": case "five": case "forty": case "fifty": continue;
						  }
						  break;
				case 'h': switch ( w ) {
						  case "half": case "hundred": continue;
						  }
						  break;
				case 'l': if ( w.equals( "last" ) ) continue;
						  break;
				case 'm': switch ( w ) {
						  case "million" : case "middle" : continue;
						  }
						  break;
				case 'n': switch ( w ) {
						  case "nin" /* ninth */ : case "nine": case "ninety" : continue;
						  }
						  break;
				case 'o': if ( w.equals( "one" ) ) continue;
						  break;
				case 'q': if ( w.equals( "quarter" ) ) continue;
						  break;
				case 's': switch ( w ) {
						  case "second": case "six": case "seven": case "sixty": case "seventy": continue;
						  }
				case 't': switch ( w ) {
						  case "third": case "two": case "three": case "ten": case "twelve": case "twenty": case "thirty": case "thousand": continue;
						  }
						  break;
				case 'z': if ( w.equals( "zero" ) ) continue;
						  break;
				}
			}
			
			// Remove Articles
			if ( true == rmArticle ) {
				switch ( word ) {
				case "a": case "an": case "the": continue;
				}
			}
			
			// Remove Demonstratives
			if ( true == rmDemonstrative ) {
				if ( word.charAt(0) == 't' ) {
					switch ( word ) {
					case "thi" /* this */: case "that": case "these": case "those" : continue;
					}
				}
			}
			
			// Remove Conjunctions
			// NOTE, should we consider testing if coordinating conjunction is actually a coordinating conjunction?
			//		 left out '[as] much' and 'not [only]'
			if ( true == rmConjunction ) {
				switch ( word.charAt(0) ) {
				case 'a': switch ( word ) {
						  case "and": case "also": case "as": continue;
						  }
						  break;
				case 'b': switch ( word ) {
						  case "but": case "both": continue;
						  }
						  break;
				case 'e': if ( word.equals( "either" ) ) continue;
						  break;
				case 'f': if ( word.equals( "for" ) ) continue;
						  break;
				case 'j': if ( word.equals( "just" ) ) continue;
						  break;
				case 'n': switch ( word ) {
						  case "nor": case "neither": continue;
						  }
						  break;
				case 'o': switch ( word ) {
						  case "or": case "only": continue;
						  }
						  break;
				case 's': if ( word.equals( "so" ) ) continue;
						  break;
				case 't': switch ( word ) {
						  case "than": case "then": continue;
						  }
						  break;
				case 'w': switch ( word ) {
						  case "with": case "whether": continue;
						  }
						  break;
				case 'y': if ( word.equals( "yet" ) ) continue;
						  break;

				}
			}
			
			// Remove Prepositions
			if ( true == rmPreposition ) {
				switch ( word.charAt(0) ) {
				case 'a': switch ( word ){
						  case "at": case "after": case "above": case "across": continue;
						  }
						  break;
				case 'b': switch ( word ){
						  case "before": case "besides": case "behind": case "below": case "beneath": case "between": continue;
						  }
						  break;
				case 'd': if ( word.equals( "down" ) ) continue;
						  break;
				case 'f': if ( word.equals( "far" ) ) continue;
						  break;
				case 'h': if ( word.equals( "here" ) ) continue;
						  break;
				case 'i': switch ( word ) {
						  case "in": case "into": case "instead": case "inside": continue;
						  }
						  break;
				case 'n': switch ( word ) {
						  case "near": case "next": continue;
						  }
						  break;
				case 'o': switch ( word ) {
						  case "out": case "over": case "on": case "of": case "outside": continue;
						  }
						  break;
				case 't': switch ( word ) {
						  case "there": case "through": case "throughout": continue;
						  }
						  break;
				case 'u': switch ( word ) {
						  case "under": case "underneath": case "up": case "until": case "upon": continue;
						  }
						  break;
				case 'w': switch ( word ) {
						  case "with": case "within": continue;
						  }
						  break;
				}
			}
			
			// Remove Question Words (who, what, ...)
			if ( true == rmQuestion ) {
				switch ( word.charAt(0) ) { 
				case 'h': if ( word.equals( "how" ) ) continue;
						  break;
				case 'w': switch ( word ) {
						  case "what": case "where": case "who": case "when": case "which": case "why": continue;
						  }
						  break;
				}
			}
			
			// compare to stem version of the word
			String w;
			if ( false == stemmed ) {
				Stemming stem = new Stemming( new String[]{ word } );
				w = stem.replace()[0];
			}
			else
				w = word;
			
			// Remove Pronouns
			// Excluded 'whom', 'whose'
			if ( true == rmPronoun ) {
				switch ( word.charAt(0) ) {
				case 'a': switch ( w ) {
						  case "anybody": case "anyone": case "anything": continue;
						  }
						  break;
				case 'e': switch ( w ) {
						  case "each": case "everybody": case "everyone": case "everything": continue;
						  }
						  break;
				case 'h': switch ( w ) {
						  case "he": case "him": case "his": case "her": case "herself": case "himself": continue;
						  }
						  break;
				case 'i': switch ( w ) {
						  case "i": case "it": case "its": case "itself": continue;
						  }
						  break;
				case 'm': switch ( w ) {
						  case "me": case "my": case "myself": continue;
						  }
						  break;
				case 'n': switch ( w ) {
						  case "nobody": case "nothing": continue;
						  }
						  break;
				case 'o': switch ( w ) {
						  case "our": case "ourself": case "oneself": continue;
						  }
						  break;
				case 's': switch ( word ) {
						  case "she": case "somebody": case "something": case "someone": case "self": continue;
						  }
						  break;
				case 't': switch ( word ) {
						  case "that": case "they": case "them": case "their": case "thee": case "themselves": 
						  case "thine": case "thou": case "thyself": continue;
						  }
						  break;
				case 'u': if ( word.equals( "us" ) ) continue;
						  break;
				case 'w': switch ( word ) {
						  case "we": case "whoev" /* whoever */: case "whom": case "whomev" /* whomever */: case "whichev" /* whichever */: continue;
						  }
						  break;
				case 'y': switch ( word ) {
						  case "you": case "your": case "yourself": case "ye": continue;
						  }
						  break;
				}
			}
			
			// Remove Quantifiers
			if ( true == rmQuantifier ) {
				switch ( word.charAt(0) ) {
				case 'a': switch ( w ) {
						  case "all": case "any": continue;
						  }
						  break;
				case 'b': switch ( w ) {
						  case "both": case "big": continue;
						  }
						  break;
				case 'f': switch ( w ) {
						  case "few": continue;
						  }
						  break;
				case 'h': switch ( word ) {
						  case "high": continue;
						  }
						  break;
				case 'l': switch ( word ) {
						  case "large": case "less": case "long": case "low": case "least": continue;
						  }
						  break;
				case 'm': switch ( word ) {
						  case "many": case "most": case "more": continue;
						  }
						  break;
				case 'n': switch ( word ) {
						  case "none": case "non": continue;
						  }
						  break;
				case 'o': switch ( word ) {
						  case "often": case "once": continue;
						  }
						  break;
				case 's': switch ( word ) {
						  case "some": case "several": case "small": case "short": continue;
						  }
						  break;
				case 't':  switch ( word ) {
						  case "tiny": continue;
						  }
						  break;
				}
			}
			
			// Remove Porter List of Stop Words
			if ( true == rmPorter ) {
				switch ( word.charAt(0) ) {
				case 'a': switch ( word ) {
						  case "aboard": case "about": case "again": case "against": case "almost":
						  case "alone": case "along": case "alongside": case "already": case "although": case "alway" /* always */ : case "am":
						  case "amid": case "amidst": case "among": case "amongst": case "another": case "anti": case "anywhere": 
						  case "are": case "area": case "around": case "ask": case "astride": case "away": case "at": continue;	 
						  }
						  break;
				case 'b': switch ( word ) {	// not include better
						  case "back": case "bar": case "be": case "became": case "because": case "become": case "been": case "begin":
						  case "being": case "best": case "beyond": case "by": continue;
						  }
						  break;
				case 'c': switch ( word ) {
						  case "came": case "can": case "cannot": case "certain": 
						  case "circa": case "clear": case "come": case "consider": case "concern": case "could": continue;
						  }
						  break;
				case 'd': switch ( word ) { // exclude daren't
						  case "despite": case "did": case "differ": case "do": case "dur" /* during */ : continue; 
						  }
						  break;
				case 'e': switch ( word ) { 
						  case "each": case "end": case "enough": case "even": case "ever": case "every": case "except": 
						  case "exclud" /* excluding */ : continue; 
						  }
						  break;
				case 'f': switch ( word ) { 
						  case "face": case "fact": case "felt": case "find": case "follow": case "for": case "from": case "full":
						  case "furth" /* further */: case "ful" /* fully */ : continue;
						  }
						  break;
				case 'g': switch ( word ) { 
						  case "gave": case "general": case "get": case "give": case "given": case "go": case "good": case "got":
						  case "great": case "group": continue;
						  }
						  break;
				case 'h': switch ( word ) { 
						  case "had": case "has": case "have": case "hav" /* having */: case "here": case "however": continue;
						  }
						  break;
				case 'i': switch ( word ) {
						  case "if": case "ilk": case "important": 
						  case "includ" /* including */: case "interest": case "is": case "it": continue;
						  }
				case 'j': switch ( word ) {
						  case "just": continue;
						  }
						  break;
				case 'k': switch ( word ) {
						  case "keep": case "kind": case "knew": case "know": case "known": continue;
						  }
						  break;
				case 'l': switch ( word ) {
						  case "later": case "let": case "like": continue;
						  }
						  break;
				case 'm': switch ( word ) {
						  case "made": case "make": case "mak" /* making */: case "man": case "may": case "member": 
						  case "men": case "might": case "mine": case "minus": 
						  case "mr": case "mrs": case "ms": case "much": continue;
						  }
						  break;
				case 'n': switch ( word ) {
						  case "naught": case "necessary": case "need": case "never":
						  case "new": case "no": case "not": case "noth" /* nothing */:
						  case "notwithstand": case "now": case "nowhere": case "number": continue;
						  }
						  break;
				case 'o': switch ( word ) {
					      case "off": case "old": case "only":
						  case "onto": case "open": case "opposite": case "ord" /* order */: case "oth" /* other */: case "otherwise":
						  case "ought": case "own": continue;
						  }
						  break;
				case 'p': switch ( word ) {
					      case "part": case "past": case "pend" /* pending */: case "per": case "perhap" /* perhaps */:
						  case "place": case "plus": case "point": case "possible": case "present": case "problem":
						  case "put": continue;
						  }
						  break;
				case 'q': switch ( word ) {
					      case "quite": continue;
						  }
						  break;
				case 'r': switch ( word ) {
					      case "rather": case "real" /* really */: case "regard": case "right": case "room": case "round": continue;
						  }
						  break;
				case 's': switch ( word ) {
					      case "said": case "same": case "say": case "save": case "second": case "see": case "seem":
						  case "seen": case "several": case "shall": case "should":
						  case "show": case "side": case "since": case "so": case "somewhat": case "somewhere": 
						  case "state": case "still": case "such": case "suchlike": case "sundry": case "sure": continue;
						  }
						  break;
				case 't': switch ( word ) {
					      case "take": case "taken": case "therefore": case "thing":
						  case "think": case "though": case "thought": case "thus": case "till": case "to": 
						  case "today": case "together": case "too": case "took": case "toward": case "turn":
						  case "twain": continue;
						  }
						  break;
				case 'u': switch ( word ) { // exclude using/used
					      case "unless": case "unlike": case "use": continue;
						  }
						  break;
				case 'v': switch ( word ) {
					      case "various": case "versus": case "very": case "via": case "vis" /* vis-a-vis */: continue; 
						  }
						  break;
				case 'w': switch ( word ) { // exclude which, why
					      case "want": case "was": case "way": case "were": case "went": case "whatall": case "whatev" /* whatever */:
						  case "whatsoev" /* whatsoever */: case "whereas": case "wherewith": case "wherewithal": case "wheth" /* whether */:
						  case "whichev" /* whichever */: case "whichsoev" /* whichsoever */: case "while": case "whoev" /* whoever */: case "whole":
						  case "whomso": case "whosoev" /* whosoever */: case "whose": case "will": case "with":
						  case "without": case "work": case "would": case "worth": case "whenev" /* whenever */: continue; 
						  } 
				case 'y': switch ( word ) {
					      case "year": case "yes" : case "yet": case "yon": case "yonder": case "young": continue;
						  }
						  break;
				}
			}
			
			// Check if word is a word to additional exclude
			if ( null != excludeWords ) {
				boolean found = false;
				for ( String exclude : excludeWords ) {
					if ( exclude.equals( word ) ) {
						found = true;
						break;
					}
				}
				if ( true == found )
					continue;
			}
			
			// Add the word to the new list (to return)
			newWords.add( word );
		}

		// return the new list
		return newWords.toArray( new String[ newWords.size() ] );
	}
}
