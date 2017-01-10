package com.wp.parser;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

public class TestSearch {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String indexDir="F:\\IDEA\\Luence\\src\\main\\java\\com\\wp\\parser\\index";
		Directory dir=FSDirectory.getDirectory(indexDir);
		IndexReader reader = IndexReader.open(dir);
		System.out.println(reader.numDocs());
		reader.close();
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		Term term=new Term("address","*º£*");
//		TermQuery query=new TermQuery(term);
//		PrefixQuery query=new PrefixQuery(term);
		WildcardQuery query=new WildcardQuery(term);
		TopDocs topDocs=searcher.search(query, 100);
		hits=topDocs.scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
			System.out.print(hits[i].score+" ");
			System.out.print(doc.get("id")+" ");
			System.out.print(doc.get("name")+" ");
			System.out.print(doc.get("address")+" ");
			System.out.println(doc.get("birthday")+" ");
		}
		searcher.close();
		dir.close();

	}

}

