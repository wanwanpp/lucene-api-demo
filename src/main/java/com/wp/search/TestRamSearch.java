package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/9 0009.
 */
public class TestRamSearch {


    public static Directory directory = new RAMDirectory();

    public static void main(String[] args) throws IOException {
        creatRamIndex();
        TestIndex testIndex = new TestIndex();
        testIndex.creatRamIndex();
        IndexSearcher searcher = new IndexSearcher(directory);
        Term term = new Term("address","nanjing");
        TermQuery query = new TermQuery(term);
        TopDocs topDocs = searcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i =0;i<scoreDocs.length;i++){
            System.out.print(scoreDocs[i].score+" ");
            Document document = searcher.doc(scoreDocs[i].doc);
            System.out.print(document.get("id")+" ");
            System.out.print(document.get("name")+" ");
            System.out.print(document.get("address")+" ");
            System.out.println(document.get("birthday")+" ");
        }
        searcher.close();
        directory.close();
    }

    public static void creatRamIndex() throws IOException{
        String [] ids={"1","2","3","4"};
        String [] names={"ZHangsan","lisi","wangwu","zhaoliu"};
        String [] addresses={"tianjing","nanjing","beijing","nanning"};
        String [] birthdays={"19820720","19840203","19770409","19830130"};
        Analyzer analyzer=new StandardAnalyzer();
        //true 表示创建或覆盖当前索引；false表示对当前索引进行追加
        //Default value is 128
        IndexWriter writer=new IndexWriter(directory,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);

        for(int i=0;i<ids.length;i++){
            Document document=new Document();
            document.add(new Field("id",ids[i],Field.Store.YES,Field.Index.ANALYZED));
            document.add(new Field("name",names[i],Field.Store.YES,Field.Index.ANALYZED));
            document.add(new Field("address",addresses[i],Field.Store.YES,Field.Index.ANALYZED));
            document.add(new Field("birthday",birthdays[i],Field.Store.YES,Field.Index.ANALYZED));
            writer.addDocument(document);
        }

        writer.optimize();

        writer.close();
        System.out.println("ok");
    }
}
