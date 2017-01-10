package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

public class TestTopDocCollector {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Directory dir=FSDirectory.getDirectory(TestIndex.INDEX_DIR);
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		TopDocCollector topDocColl=new TopDocCollector(10);
		Term term=new Term("address","nanjing");
		TermQuery query=new TermQuery(term);
		searcher.search(query,topDocColl);
		hits=topDocColl.topDocs().scoreDocs;
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
