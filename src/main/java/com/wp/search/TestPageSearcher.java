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

/**
 * Created by 王萍 on 2017/1/9 0009.
 */
public class TestPageSearcher {

    public static void main(String[] args) throws IOException {
        Directory dir= FSDirectory.getDirectory(TestIndex.INDEX_DIR);
        IndexSearcher searcher=new IndexSearcher(dir);
        ScoreDoc[] hits=null;
        Term term=new Term("contents","*onto*");
        WildcardQuery query=new WildcardQuery(term);

        TopDocs topDocs=searcher.search(query, 126);

        int pageNumber=6;//第pageNumbe最小的页码数是1；
        int eachePageNum=10;//每页显示的个数


        hits=topDocs.scoreDocs;

        System.out.println(hits.length);
        int totalNumber=pageNumber*eachePageNum;
        if(totalNumber>hits.length)totalNumber=hits.length;
        for(int i=(pageNumber-1)*eachePageNum;i<totalNumber;i++){
            Document doc=searcher.doc(hits[i].doc);
            System.out.print(hits[i].doc);
            System.out.print(hits[i].score+" ");
            System.out.println(doc.get("contents")+" ");
        }
        searcher.close();
        dir.close();

    }
}
