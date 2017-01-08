package com.wp;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */

public class TestLucene {

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("hello",new StringReader("Welcome to use lucene"));
        Token token = new Token();
        while (tokenStream.next(token)!=null){
            System.out.println(token.term());
        }

    }
}
