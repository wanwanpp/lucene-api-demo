package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

public class TestWildcardQuery {

	/**
	 * 通配符查询
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Directory dir=FSDirectory.getDirectory(TestIndex.INDEX_DIR);
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		Term term=new Term("name","*g??");
		WildcardQuery query=new WildcardQuery(term);
		
		TopDocs topDocs=searcher.search(query, 10);
		
		hits=topDocs.scoreDocs;
		for(int i=0;i<hits.length;i++){
			Document doc=searcher.doc(hits[i].doc);
//		    System.out.print(hits[i].score+" ");
			System.out.print(doc.get("id")+" ");
			System.out.print(doc.get("name")+" ");
			System.out.print(doc.get("address")+" ");
			System.out.println(doc.get("birthday")+" ");
		}
		searcher.close();
		dir.close();
		
	}



}
