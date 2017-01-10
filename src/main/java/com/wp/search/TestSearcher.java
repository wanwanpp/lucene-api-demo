package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */

public class TestSearcher {

    public static void main(String[] args) throws IOException {
        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);
        IndexSearcher searcher = new IndexSearcher(directory);
        ScoreDoc[] hits = null;
        Term term = new Term("address","beijing");
        TermQuery termQuery = new TermQuery(term);
        TopDocs topDocs = searcher.search(termQuery,10);
        hits=topDocs.scoreDocs;
        for (int i=0;i<hits.length;i++){

            //获取文档
            Document document = searcher.doc(hits[i].doc);
            System.out.print(hits[i].score+" ");
            System.out.print(document.get("id")+" ");
            System.out.print(document.get("name")+" ");
            System.out.println(document.get("address"));
        }
        searcher.close();
        directory.close();
    }
}
