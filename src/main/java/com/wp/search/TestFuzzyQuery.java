package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestFuzzyQuery {

    /**
     * 模糊查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);
        IndexSearcher indexSearcher = new IndexSearcher(directory);

        //根据name查不出，因为创建索引时，没有对name创建相应的索引
//        Term term = new Term("name","zhangsan");

        Term term = new Term("address","beijing");
        FuzzyQuery fuzzyQuery =  new FuzzyQuery(term);
        TopDocs topDocs = indexSearcher.search(fuzzyQuery, 10);
        ScoreDoc[] hits = topDocs.scoreDocs;
        for (int i=0;i<hits.length;i++){
            Document document = indexSearcher.doc(hits[i].doc);
            System.out.print(hits[i].score+" ");
            System.out.print(document.get("id")+" ");
            System.out.print(document.get("name")+" ");
            System.out.println(document.get("address")+" ");
        }
        indexSearcher.close();
        directory.close();
    }
}
