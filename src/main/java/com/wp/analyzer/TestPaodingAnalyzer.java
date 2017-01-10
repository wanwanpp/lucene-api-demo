package com.wp.analyzer;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

import java.io.IOException;
import java.io.StringReader;

public class TestPaodingAnalyzer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Analyzer analyzer=new PaodingAnalyzer();
		String input="该词起源于明治维新日本国门打开后,本称吴服,是因为它是在中国东吴江浙一带的汉服的基础上演变而来的,后因日本人是和族所以也叫和服";
		TokenStream ts = analyzer.tokenStream("", new StringReader(input));
		Token token=new Token();
		while((token=ts.next(null))!=null){
			System.out.println(token.term());
		}

	}

}
