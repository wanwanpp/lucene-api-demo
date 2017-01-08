package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestPrefixQuery {

    /**
     * 根据前缀查询
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);

        IndexSearcher searcher = new IndexSearcher(directory);
        Term term = new Term("address","be");
        PrefixQuery query = new PrefixQuery(term);
        TopDocs topDocs = searcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i=0;i<scoreDocs.length;i++){
            Document document = searcher.doc(scoreDocs[i].doc);
            System.out.print(scoreDocs[i].score+" ");
            System.out.print(document.get("id")+" ");
            System.out.print(document.get("name")+" ");
            System.out.print(document.get("address"));
        }
        searcher.close();
        directory.close();
    }
}
