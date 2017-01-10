package com.wp.file;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by ??? on 2017/1/10 0010.
 */
public class TestFileSearch {
    public static void main(String[] args) throws IOException {
        Directory directory = FSDirectory.getDirectory(TestFileIndex.IndexDir);

        IndexSearcher searcher = new IndexSearcher(directory);
        long ti = System.currentTimeMillis();
        Term term = new Term("contents","ibm");
        //TermQuery匹配分词后的最小单元
        TermQuery query = new TermQuery(term);
//        PrefixQuery query = new PrefixQuery(term);
        //匹配查询
//        WildcardQuery query = new WildcardQuery(term);
        TopDocs topDocs = searcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i=0;i<scoreDocs.length;i++){
            Document document = searcher.doc(scoreDocs[i].doc);
            System.out.print(scoreDocs[i].score+" ");
            System.out.print(document.get("fileName")+" ");
            System.out.println(document.get("contents")+" ");
        }
        System.out.println(System.currentTimeMillis()-ti);
        searcher.close();
        directory.close();
    }
}
