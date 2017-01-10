package com.wp.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class TestAnalyzer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
//		Analyzer analyzer=new StandardAnalyzer();
		//处理掉标点符号
//		Analyzer analyzer=new SimpleAnalyzer();
		//按照空格进行分词
//		Analyzer analyzer=new WhitespaceAnalyzer();
		//简单中文处理，不理想
//		Analyzer analyzer=new ChineseAnalyzer();
		//按照两个汉字进行拆分
		Analyzer analyzer=new CJKAnalyzer();

		String input="该词起源于明治维新日本国门打开后,本称吴服,是因为它是在中国东吴江浙一带的汉服的基础上演变而来的,后因日本人是和族所以也叫和服";
		TokenStream ts = analyzer.tokenStream("", new StringReader(input));
		Token token=new Token();
		while(ts.next(token)!=null){
			System.out.println(token.term());
		}

	}

}
