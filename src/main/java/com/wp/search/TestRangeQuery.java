package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestRangeQuery {

    public static void main(String[] args) throws IOException {

        /**
         * 按照范围查询
         */
        Directory dir= FSDirectory.getDirectory(TestIndex.INDEX_DIR);
        IndexSearcher searcher=new IndexSearcher(dir);
        ScoreDoc[] hits=null;
        Term beginTerm=new Term("birthday","19820720");
        Term endTerm=new Term("birthday","19830130");
        RangeQuery query=new RangeQuery(beginTerm,endTerm,true);


        TopDocs topDocs=searcher.search(query, 2);

        hits=topDocs.scoreDocs;
        for(int i=0;i<hits.length;i++){
            Document doc=searcher.doc(hits[i].doc);
            System.out.print(doc.get("id")+" ");
            System.out.print(doc.get("name")+" ");
            System.out.print(doc.get("address")+" ");
            System.out.println(doc.get("birthday")+" ");
        }
        searcher.close();
        dir.close();
    }
}