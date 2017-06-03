package epipog.bow;

import java.util.ArrayList;

// Class Definition for Common Word Reduce
//
public class Reduce {
	private String[] words	  		   = null;		// original list of words
	
	// Constructor
	public Reduce( String[] words ) { 
		this.words = words;
	}
	
	// Replace word with common equivalent
	public String[] replace() {
		// Check each word in the list
		for ( int i = 0; i < words.length; i++ ) {
			if ( words[ i ].length() < 3 )
				continue;
			
			switch ( words[ i ].charAt( 0 ) ) {
			case 'a': switch ( words[ i ].charAt( 1 ) ) {
					  case 'd': switch ( words[ i ] ) {
								case "add": words[ i ] = "addition"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "airforce": words[ i ] = "military"; continue;
								}
								break;
					  case 'm': switch ( words[ i ] ) {
								case "american": words[ i ] = "america"; continue;
								}
								break;
					  case 'n': switch ( words[ i ] ) {
								case "answerable": words[ i ] = "answer"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "army": words[ i ] = "military"; continue;
								}
								break;
					  case 't': switch ( words[ i ] ) {
								case "ate": words[ i ] = "eat"; continue;
								}
								break;
					  }	
					  break;
			case 'b': switch ( words[ i ].charAt( 1 ) ) {
					  case 'i': switch ( words[ i ] ) {
								case "big"  :
								case "bigg" : words[ i ] = "large"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "beyound" : words[ i ] = "beyond"; continue;
								case "beautiful":
								case "beautify": words[ i ] = "beauty"; continue;
								case "best"    : words[ i ] = "good"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "boy" : words[ i ] = "male"; continue;
								case "boob": words[ i ] = "breast"; continue;
								case "born": words[ i ] = "birth"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "brother": words[ i ] = "sibling"; continue;
								case "bride"  : words[ i ] = "wife"; continue;
								case "british": words[ i ] = "britain"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "built": words[ i ] = "build"; continue;
								}
								break;
					  }	
					  break;
			case 'c': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "cattle": words[ i ] = "cow"; continue;
								case "car"   : words[ i ] = "automobile"; continue;
								case "career": words[ i ] = "job"; continue;
								case "cash"  : words[ i ] = "money"; continue;
								}
								break;
					  case 'h': switch ( words[ i ] ) {
								case "children": words[ i ] = "child"; continue;
								case "choice"  : words[ i ] = "option"; continue;
								case "christ"  : words[ i ] = "jesus"; continue;
								case "chinese" : words[ i ] = "china"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "colour"  : words[ i ] = "color"; continue;
								case "complete": words[ i ] = "finish"; continue;
								case "college": words[ i ] = "university"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "criminal": words[ i ] = "crime"; continue;
								}
								break;
					  }	
					  break;
			case 'd': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "dad"     : words[ i ] = "father"; continue;
								case "daddy"   : words[ i ] = "father"; continue;
								case "daughter": words[ i ] = "child"; continue;
								case "dangerous": words[ i ] = "danger"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "dead": words[ i ] = "death"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "divide": words[ i ] = "division"; continue;
								case "die":
								case "died"  : words[ i ] = "death"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "does"  : words[ i ] = "do"; continue;
								case "doing" : words[ i ] = "do"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "drank": words[ i ] = "drink"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "dude": words[ i ] = "male"; continue;
								}
								break;
					  }	
					  break;
			case 'e': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "eaten": words[ i ] = "eat"; continue;
								}
								break;
					  case 'l': switch ( words[ i ] ) {
								case "electrical": words[ i ] = "electricity"; continue;
								}
								break;
					  case 'm': switch ( words[ i ] ) {
								case "emotional": words[ i ] = "emotion"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "european": words[ i ] = "europe"; continue;
								}
								break;
					  case 'v': switch ( words[ i ] ) {
								case "evening": words[ i ] = "night"; continue;
								}
								break;
					  case 'x': switch ( words[ i ] ) {
								case "expressway": words[ i ] = "road"; continue;
								}
								break;
					  case 'y': switch ( words[ i ] ) {
								case "eyes": words[ i ] = "eye"; continue;
								}
								break;
					  }	
					  break;
			case 'f': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "favourite": words[ i ] = "favorite"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "feet": words[ i ] = "foot"; continue;
								case "fell": words[ i ] = "fall"; continue;
								case "felt": words[ i ] = "feel"; continue;
								}
								break;
					  case 'l': switch ( words[ i ] ) {
								case "flock": words[ i ] = "group"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "forgot":
								case "forgotten": words[ i ] = "forget"; continue;
								case "found"    : words[ i ] = "find"; continue;
								case "fought"   : words[ i ] = "fight"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "freeway": words[ i ] = "road"; continue;
								case "french": words[ i ] = "france"; continue;
								}
								break;
					  }	
					  break;
			case 'g': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "gal"  : words[ i ] = "female"; continue;
								case "gave" : words[ i ] = "give"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "gentleman" : words[ i ] = "male"; continue;
								case "gentlemen" : words[ i ] = "male"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "girl": words[ i ] = "female"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "going":
								case "goes" : words[ i ] = "go"; continue;
								case "got"  : words[ i ] = "get"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "groom" : words[ i ] = "husband"; continue;
								case "grew"  :
								case "growth": words[ i ] = "grow"; continue;
								case "graduation": words[ i ] = "graduate"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "guy" : words[ i ] = "male"; continue;
								}
								break;
					  }	
					  break;
			case 'h': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "happiness" : words[ i ] = "happy"; continue;
								case "harassment": words[ i ] = "harass"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "hen"    : words[ i ] = "chicken"; continue;
								case "herd"   : words[ i ] = "group"; continue;
								case "healthy": words[ i ] = "health"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "highway" : words[ i ] = "road"; continue;
								case "hindi"   : words[ i ] = "hindu"; continue;
								case "historical": words[ i ] = "history"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "home" : words[ i ] = "house"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "human" : words[ i ] = "person"; continue;
								}
								break;
					  }	
					  break;
			case 'i': switch ( words[ i ].charAt( 1 ) ) {
					  case 's': switch ( words[ i ] ) {
							    case "islamic":
								case "islam": words[ i ] = "muslim"; continue;
								}
								break;
					  case 'm': switch ( words[ i ] ) {
								case "improvement" : words[ i ] = "improve"; continue;
								case "importance"  : words[ i ] = "important"; continue;
								}
								break;
					  }	
					  break;
			case 'j': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "japanese": words[ i ] = "japan"; continue;
								}
								break;
					  }	
					  break;
			case 'k': switch ( words[ i ].charAt( 1 ) ) {
					  case 'i': switch ( words[ i ] ) {
								case "kitty" : words[ i ] = "cat"; continue;
								case "kitten": words[ i ] = "cat"; continue;
								case "kid"   : words[ i ] = "child"; continue;
								case "kinder": words[ i ] = "child"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "korean": words[ i ] = "korea"; continue;
								}
								break;
					  }	
					  break;
			case 'l': switch ( words[ i ].charAt( 1 ) ) {
					  case 'e': switch ( words[ i ] ) {
								case "legal": words[ i ] = "law"; continue;
								}
								break;
					  case 'a': switch ( words[ i ] ) {
								case "lady": words[ i ] = "female"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "little": words[ i ] = "small"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "lucifer": words[ i ] = "devil"; continue;
								}
								break;
					  }	
					  break;
			case 'm': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "man"     : words[ i ] = "male"; continue;
								case "marriage": words[ i ] = "marry"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "men": words[ i ] = "male"; continue;
								case "met": words[ i ] = "meet"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "mom"  : words[ i ] = "mother"; continue;
								case "mommy": words[ i ] = "mother"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "multiply": words[ i ] = "multiplication"; continue;
								}
								break;
					  }	
					  break;
			case 'n': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "navy": words[ i ] = "military"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "numerical": words[ i ] = "number"; continue;
								}
								break;
					  }	
					  break;
			case 'o': switch ( words[ i ].charAt( 1 ) ) {
					  case 'p': switch ( words[ i ] ) {
								case "optional": words[ i ] = "option"; continue;
								}
								break;
					  }	
					  break;
			case 'p': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "pakistani": words[ i ] = "pakistan"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "people"    : words[ i ] = "person"; continue;
								case "percentage": words[ i ] = "percent"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "pony": words[ i ] = "horse"; continue;
								case "powerful": words[ i ] = "power"; continue;
								}
								break;
					  case 'h': switch ( words[ i ] ) {
								case "photo": words[ i ] = "photograph"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "progamme"    : words[ i ] = "program"; continue;
								case "presidential": 
								case "presidency"  : words[ i ] = "president"; continue;
								case "proposal"    : words[ i ] = "propose"; continue;
								case "preparation" : words[ i ] = "prepare"; continue;
								case "pretty"      : words[ i ] = "beauty"; continue;
								case "professor"   : words[ i ] = "teacher"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "pup"  : words[ i ] = "dog"; continue;
								case "puppy": words[ i ] = "dog"; continue;
								}
								break;
					  }	
					  break;
			case 'r': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "run": words[ i ] = "ran"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "responsive": words[ i ] = "response"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "rooster": words[ i ] = "chicken"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "run": words[ i ] = "ran"; continue;
								case "russian": words[ i ] = "russia"; continue;
								}
								break;
					  }	
					  break;
			case 's': switch ( words[ i ].charAt( 1 ) ) {
					  case 'a': switch ( words[ i ] ) {
								case "safety": words[ i ] = "safe"; continue;
								case "sat"   : words[ i ] = "sit"; continue;
								}
								break;
					  case 'c': switch ( words[ i ] ) {
								case "scream": words[ i ] = "yell"; continue;
								case "scientific": words[ i ] = "science"; continue;
								}
								break;
					  case 'e': switch ( words[ i ] ) {
								case "sexual": words[ i ] = "sex"; continue;
								case "sent"  : words[ i ] = "send"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "sister": words[ i ] = "sibling"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "son" : words[ i ] = "child"; continue;
								case "sold": words[ i ] = "sell"; continue;
								}
								break;
					  case 'p': switch ( words[ i ] ) {
								case "speak":
								case "spoken":
								case "spoke": words[ i ] = "talk"; continue;
								}
								break;
					  case 't': switch ( words[ i ] ) {
								case "staff": words[ i ] = "employee"; continue;
								case "stood": words[ i ] = "stand"; continue;
								}
								break;
					  case 'u': switch ( words[ i ] ) {
								case "subtract"  : words[ i ] = "subtraction"; continue;
								case "successful": words[ i ] = "success"; continue;
								case "suggestion": words[ i ] = "suggest"; continue;
								}
								break;
					  }	
					  break;
			case 't': switch ( words[ i ].charAt( 1 ) ) {
					  case 'e': switch ( words[ i ] ) {
								case "technological": words[ i ] = "technical"; continue;
								}
								break;
					  case 'o': switch ( words[ i ] ) {
								case "told": words[ i ] = "tell"; continue;
								case "town": words[ i ] = "city"; continue;
								}
								break;
					  }	
					  break;
			case 'u': switch ( words[ i ].charAt( 1 ) ) {
					  case 's': switch ( words[ i ] ) {
								case "uses":
								case "used":
								case "using": words[ i ] = "use"; continue;
								case "understood": words[ i ] = "understand"; continue;
								}
								break;
					  }	
					  break;
			case 'v': switch ( words[ i ].charAt( 1 ) ) {
					  case 'e': switch ( words[ i ] ) {
								case "vehicle": words[ i ] = "automobile"; continue;
								}
								break;
					  case 'i': switch ( words[ i ] ) {
								case "village": words[ i ] = "city"; continue;
								}
								break;
					  }	
					  break;
			case 'w': switch ( words[ i ].charAt( 1 ) ) {
					  case 'o': switch ( words[ i ] ) {
								case "woman": words[ i ] = "female"; continue;
								case "women": words[ i ] = "female"; continue;
								case "worst": words[ i ] = "bad"; continue;
								case "won"  : words[ i ] = "win"; continue;
								case "wore" : words[ i ] = "wear"; continue;
								}
								break;
					  case 'r': switch ( words[ i ] ) {
								case "written":
								case "wrote"  : words[ i ] = "write"; continue;
								}
								break;
					  }	
					  break;
			}
		}
		
		return words;
	}
}
