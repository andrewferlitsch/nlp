
import epipog.parse.*;
import epipog.bow.*;

import java.util.ArrayList;
import java.io.File;

public class nlp {
	final static String usage = "Usage: nlp <options> file\r\n" +
								"\t-c cols\t# columns to process\r\n" +
								"\t-e word-list\t# words to exclude (comma separated)\r\n" +
								"\t-f w|d\t\t# word counts (w = percentage of words, d = percentage in docs)\r\n" +
								"\t-F\t\t# word counts only\r\n" +
								"\t-k word-list\t# words to keep (comma separated)\r\n" +
								"\t-l\t\t# lemmatization\r\n" +
								"\t-n\t\t# no header (csv)\r\n" +
								"\t-r\t\t# word reduction\r\n" +
								"\t-R reader\t# reader type (mem,line,mapped)\r\n" +
								"\t-s\t\t# stemming\r\n" +
								"\t-S sflags\t# stop word groups\r\n" +
								"\t-t type\t\t# input file type\r\n";
	
	public static void main( String args[] ) {
		
		// Check for command line argument are present
		if ( args.length < 2 ) {
			System.err.print( usage );
			System.exit( 1 );
		}
		
		String  cOption = null;		// columns to process
		String  eOption = null;		// Stop Words to exclude
		String  fOption = null;		// Word Frequency
		Boolean FOption = false;	// Word Frequency only
		String  kOption = null;		// Stop Words to keep
		Boolean lOption = false;	// Lemmatization
		Boolean nOption = false;	// no header (csv)
		Boolean rOption = false;	// Word Reduction
		String  ROption = "line";	// Reader type (default is line)
		Boolean sOption = false;	// Stemming
		String  SOption = "p";		// stop word groups to filter
		String  tOption = null;		// Input File Type 

		char opt;
		while ( ( opt = GetOpt.Parse( args, "c:e:f:Fk:lnrR:sS:t:", usage ) ) != (char)-1 ) {
			switch ( opt ) {
			case 'c': cOption = GetOpt.Arg(); 	break;
			case 'e': eOption = GetOpt.Arg(); 	break;
			case 'F': FOption = true; 			break;
			case 'f': fOption = GetOpt.Arg();  	break;
			case 'k': kOption = GetOpt.Arg(); 	break;
			case 'l': lOption = true; 		  	break;
			case 'n': nOption = true; 		  	break;
			case 'r': rOption = true; 		  	break;
			case 'R': ROption = GetOpt.Arg(); 	break;
			case 's': sOption = true; 		  	break;
			case 'S': SOption = GetOpt.Arg(); 	break;
			case 't': tOption = GetOpt.Arg(); 	break;
			}
		}

		// Import the file
		String dataFile = args[ args.length - 1 ];
		File f = new File( dataFile );
		if ( !f.exists() ) {
			System.err.println("File does not exist: " + dataFile );
			System.exit( 1 );
		}
			
		// Attempt to determine file type from File Suffix
		if ( null == tOption ) {
			switch ( dataFile.substring( dataFile.lastIndexOf(".") + 1 ).toLowerCase() )
			{
			case "csv" : tOption = "csv";  break;
			case "psv" : tOption = "psv";  break;
			case "tsv" : tOption = "tsv";  break;
			case "json": tOption = "json"; break;
			default    : System.err.println( "Unrecognized file type: " + dataFile );
						 System.err.println( usage);
						 System.exit( 1 );
			}
		}
			
		// Bind a parser according to the input file format
		Parse parser = null;
		switch ( tOption ) {
		case "csv" : parser = new CSVParse( dataFile );  break;
		case "psv" : parser = new PSVParse( dataFile );  break;
		case "tsv" : parser = new TSVParse( dataFile );  break;
		case "json": parser = new JSONParse( dataFile ); break;
		default    : System.err.println( "Invalid argument for -t option: " + tOption );
					 System.err.println( usage );
					 System.exit( 1 );
		}
			
		// Set the reader type for the parser
		switch ( ROption ) {
		case "mem"	 : parser.Reader( Reader.ReaderType.READERMEM );    break;
		case "line"	 : parser.Reader( Reader.ReaderType.READERLINE );   break;
		case "mapped": parser.Reader( Reader.ReaderType.READERMAPPED ); break;
		default		 : System.err.println( "Invalid argument for -R option: " + ROption );
					   System.err.println( usage );
					   System.exit( 1 );
		}
		
		// No header in input
		if ( nOption )
			parser.Header( false );
			
		// stop words to keep
		String[] keep = new String[]{ "bad", "face", "good", "not", "old", "possible", "problem" };
		if ( null != kOption ) {
			keep = kOption.split( "," );
		}
			
		// stop words to exclude (add to list)
		String[] exclude = null;
		if ( null != eOption ) {
			exclude = eOption.split( "," );
		}
			
		// stop word groups
		int sFlags = 0x0, eFlags = 0x0;
		try {
			if ( null != SOption ) {
				String[] flags = SOption.split( "," );
				for ( String flag : flags ) {
					// stop list word group to not filter
					if ( flag.charAt( 0 ) == '!' ) 
						eFlags |= SetStopFlags( flag.charAt( 1 ) );
					// stop list word group to filter
					else
						sFlags |= SetStopFlags( flag.charAt( 0 ) );
				} 
			}
		}
		catch ( ParseException e ) { System.err.println( e.getMessage() ); System.exit( 1 ); }
		
		// Columns to process for Bag of Words processing
		boolean[] fields = new boolean[ 100 ];
		for ( int i = 0; i < 100; i++ )
			fields[ i ] = false;
		if ( null != cOption ) {
			String[] cols = cOption.split( "," );
			for ( String col : cols ) {
				int nth = Integer.parseInt( col );
				if ( nth >= 100 ) {
					System.out.println( "Invalid column index, exceeds 100: " + col );
					System.exit( 1 );
				}
				fields[ nth ] = true;
			}
		}
		
		// Word Frequency option
		if ( null != fOption ) {
			switch ( fOption ) {
			case "w": break;
			case "d": break;
			default : System.err.println( "Invalid argument for -f option: " + fOption );
					  System.err.println( usage );
					  System.exit( 1 );
			}
		}
			
		// Method to process rows in input file
		Dataset dataset = new Dataset( FOption | ( null != fOption ), ( null != fOption && fOption.equals( "w" ) ) );
		dataset.stop( sFlags, eFlags );
		dataset.keep( keep );		
		dataset.exclude( exclude );
		if ( true == sOption ) dataset.stemming();
		if ( true == lOption ) dataset.lemmatization();
		if ( true == rOption ) dataset.reduce();
		dataset.columns( fields );
		( (SVParse) parser ).Processor( dataset );
			
		// Parse/Process the input file
		try {
			parser.Open();
			parser.Parse();
			parser.Close();
		}
		catch ( ParseException e ) { System.err.println( e.getMessage() ); System.exit( 1 ); }
		
		// 2nd pass
		if ( null != fOption ) {
			dataset.secondPass();
			
			// Parse/Process the input file
			try {
				parser.Open();
				parser.Parse();
				parser.Close();
			}
			catch ( ParseException e ) { System.err.println( e.getMessage() ); System.exit( 1 ); }
		}
		
		// output word frequency counts
		if ( true == FOption ) {
			dataset.counts();
		}
	}
	
	// Set Stop Words flags
	//
	private static int SetStopFlags( char opt ) throws ParseException {
		switch ( opt ) {
		case 'p': return StopWords.PORTER;		
		case 's': return StopWords.SHORTWORD;
		case 'n': return StopWords.NUMBER;
		case 'a': return StopWords.ARTICLE;
		case 'd': return StopWords.DEMONSTRATIVE; 
		case 'c': return StopWords.CONJUNCTION;
		case 'r': return StopWords.PREPOSITION; 
		case 'P': return StopWords.PRONOUN;				
		case 'q': return StopWords.QUESTION;
		case 'Q': return StopWords.QUANTIFIER;
		}
		throw new ParseException( "Invalid value for -S option: " + opt );
	}
}

// Class Definition for Processing Rows in Datasets
//
class Dataset implements SVParse.IFunction {
	private String[] keepWords    = null;	// Porter List of Words to keep 
	private String[] excludeWords = null;	// Additional words to exclude
	private int      sStopFlags   = 0x0;	// Stop Words Flags (i.e., groups of words to filter)
	private int		 eStopFlags	  = 0x0;	// Stop Words Flags to Exclude (i.e., groups of words not to filter)
	
	// List of words to include (not filter as stop words)
	public void keep( String[] keepWords ) {
		this.keepWords = keepWords;
	}
	
	// List of additional words to exclude (add to stop words)
	public void exclude( String[] excludeWords ) {
		this.excludeWords = excludeWords;
	}
	
	// Stop Word flags
	public void stop( int sStopFlags, int eStopFlags ) {
		this.sStopFlags = sStopFlags;	// (word groups to filter)
		this.eStopFlags = eStopFlags;	// (word groups to not filter)
	}
	
	private boolean stemFlag   = false;	// stemming
	private boolean lemmaFlag  = false;	// lemmatization
	private boolean reduceFlag = false;	// word reduction
	
	public void stemming()      { stemFlag = true; }
	public void lemmatization() { lemmaFlag = true; }
	public void reduce()		{ reduceFlag = true; }
	
	private boolean[] columnList = null;
	public void columns( boolean[] columnList ) { this.columnList = columnList; }
	
	private Frequency freq     = null;		// Dictionary for frequency counts
	private boolean   freqonly = false;		// frequency only 1st pass
	private boolean   wordFreq = false;		// word vs document frequency
	
	private boolean   header   = true;		// dataset has header
	private boolean   firstRow = true;		// first row in dataset
	
	public void NoHeader() { header = false; }
	
	// Second pass to output dataset as bag of words with frequency data
	public void secondPass() {
		freqonly = false;
	}
	
	// Constructor
	public Dataset( boolean freqonly, boolean wordFreq ) {
		if ( true == freqonly ) {
			Dictionary dict = new Dictionary();
			freq = new Frequency();
			freq.Processor( dict );
			this.freqonly = true;
			this.wordFreq = wordFreq;
		}
	}
	
	// List all the Word Frequency Counts
	public void counts() {
		freq.list();
	}
	
	// Process Each Row in the Dataset
	public void Process( ArrayList<String> cols ) {
		int ncols = cols.size();
		// CSV header
		if ( header && firstRow && false == freqonly ) {
			for ( int i = 0; i < ncols; i++ ) {
				if ( i != 0 ) System.out.print(",");
				System.out.print( cols.get( i ) );
			}
			System.out.println("");
			firstRow = false;
			return;
		}
		
		for ( int i = 0; i < ncols; i++ ) {
			if ( columnList[ i ] == false ) {
				if ( false == freqonly ) System.out.print( "\"" + cols.get( i ) + "\"" );
			}
			else
			{
				// Bag of Words processing of column data
				BoW bag = new BoW( cols.get( i ) );
				bag.clean();
				if ( true == lemmaFlag )
					bag.lemma();
				else if ( true == stemFlag )
					bag.stemming();
				bag.stopWords( sStopFlags, eStopFlags, keepWords, excludeWords );
				if ( true == reduceFlag )
					bag.reduce();
				
				// output in double quotes
				if ( false == freqonly ) System.out.print( "\"" );
				
				boolean first = true;
				for ( String word : bag.words() ) {
					if ( false == freqonly ) {
						if ( first == true )
							first = false;
						else
							System.out.print( "," );
						
						if ( null == freq )
							System.out.print( word );
						else {
							float percentage = ( wordFreq ) ? freq.getWP( word ) : freq.getDP( word );
							System.out.print( word + ":" + percentage );
						}
					}
					else 
						freq.add( word );
				}
				
				// end outputting in double quotes
				if ( false == freqonly ) System.out.print( "\"" );
				// Increment the document count
				else
					freq.incrDoc();
			}
			if ( false == freqonly && i < ncols - 1 ) System.out.print( "," );
		}

		if ( false == freqonly ) System.out.println("");
	}
}

// Class Definition for outputting dictionary
class Dictionary implements Frequency.IFunction {
	public void Count( String word, int count, float wfreq, float dfreq ) {
		System.out.println( word + ":" + count + "," + wfreq + "," + dfreq );
	}
}