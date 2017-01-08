package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

public class TestBooleanQuery {

	/**
	 * 布尔查询，组合查询
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Directory dir=FSDirectory.getDirectory(TestIndex.INDEX_DIR);
		IndexSearcher searcher=new IndexSearcher(dir);
		ScoreDoc [] hits=null;
		Term nterm=new Term("name","*g??");
		WildcardQuery wildcardQuery=new WildcardQuery(nterm);
		Term aterm=new Term("address","tianjing");
		TermQuery termQuery=new TermQuery(aterm);
		BooleanQuery query=new BooleanQuery();
		query.add(wildcardQuery, BooleanClause.Occur.SHOULD);
		query.add(termQuery, BooleanClause.Occur.MUST_NOT);
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
