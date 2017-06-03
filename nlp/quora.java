
import epipog.parse.*;
import epipog.bow.*;

import java.util.ArrayList;
import java.io.File;

public class quora {
	public static void main( String[] args ) {
		if ( args.length < 1 ) {
			System.err.println("too few arguments");
			System.exit(1);
		}

		// Import the file
		String dataFile = args[ args.length - 1 ];
		File f = new File( dataFile );
		if ( !f.exists() ) {
			System.err.println("File does not exist: " + dataFile );
			System.exit( 1 );
		}
		DatasetQ dataset = new DatasetQ();
		Parse parser = new CSVParse( dataFile ); 
		parser.Reader( Reader.ReaderType.READERLINE );
		( (SVParse) parser ).Processor( dataset );
		
		// Parse/Process the input file
		try {
			parser.Open();
			parser.Parse();
			parser.Close();
		}
		catch ( ParseException e ) { System.err.println( e.getMessage() ); System.exit( 1 ); }

		System.out.println( "TP: " + dataset.TP );
		System.out.println( "TN: " + dataset.TN );
		System.out.println( "FP: " + dataset.FP );
		System.out.println( "FN: " + dataset.FN );
		System.out.println( "AC: " + ( dataset.TP + dataset.TN ) / (float)dataset.total );
	}
}

// Class Definition for Processing Rows in Datasets
//
class DatasetQ implements SVParse.IFunction {
	public Integer TP = 0, FN = 0, TN = 0, FP = 0;
	public int total = 0;
	private boolean isFirst = true;
	
	// Process Each Row in the Dataset
	public void Process( ArrayList<String> cols ) {
		if ( isFirst ) { isFirst = false; return; }
		Integer isDup = Integer.parseInt( cols.get(5) );
		String[] words1 = cols.get(3).split(",");
		String[] words2 = cols.get(4).split(",");
		
		float tcount = 0;
		float tmatch = 0;
		for ( int i = 0; i < words1.length; i++ ) {
			String[] pair = words1[ i ].split(":");
			if ( pair.length == 2 ) {
				float val = Float.parseFloat( pair[ 1 ] );
				tcount += val;
				
				for ( int j = 0; j < words2.length; j++ )
					if ( words1[ i ].equals( words2[ j ] ) ) {
						words2[ j ] = null;
						tmatch += val;
						break;
					}
			}
			else
				tcount += 0.05;
		}
		
		for ( int i = 0; i < words2.length; i++ ) {
			if ( words2[ i ] != null ) {
				String[] pair = words2[ i ].split(":");
				if ( pair.length == 2 ) {
					tcount += Float.parseFloat( pair[ 1 ] );
				}
				else
					tcount += 0.05;
			}
		}
		
		Boolean matched = false;
		if ( ( tmatch / (float)tcount ) > .8 )
			matched = true;
		
		if ( matched == false ) {
			if ( isDup == 0 )
				TN++;
			else
				FN++;
		}
		else {
			if ( isDup == 1 )
				TP++;
			else
				FP++;
		}
		
		total++;
		
		/*
		if ( words1.length != words2.length ) {
			if ( isDup == 0 )
				TN++;
			else
				FN++;
		}
		else {
			Boolean predict = true;
			for ( int i = 0; i < words1.length; i++ )
				if ( !words1[i].equals( words2[i])) {
					predict = false;
					break;
				}
			if ( isDup == 1 ) {
				if ( predict )
					TP++;
				else
					FN++;
			}
			else {
				if ( !predict )
					TN++;
				else
					FP++;
			}
		}
		total++;
		*/
	}

}