package com.wp.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestUpdateDocument {
    public static void main(String[] args) throws IOException {

        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);
        IndexReader indexReader = IndexReader.open(directory);
        System.out.println("before delete:"+indexReader.numDocs());
        indexReader.deleteDocuments(new Term("id","2"));
        System.out.println("after delete:"+indexReader.numDocs());
        indexReader.close();

        Analyzer analyzer = new StandardAnalyzer();

        //第三个参数为是否覆盖之前的索引，不覆盖则追加
        IndexWriter writer = new IndexWriter(TestIndex.INDEX_DIR,analyzer,false, IndexWriter.MaxFieldLength.LIMITED);
        Document document = new Document();
        Field field = new Field("id","2", Field.Store.YES, Field.Index.ANALYZED);
        field.setBoost(1.5f);
        document.add(field);
        document.add(new Field("name","Tom", Field.Store.YES, Field.Index.NO));
        document.add(new Field("address","tianjin", Field.Store.YES, Field.Index.ANALYZED));
        document.setBoost(0.5f);
        writer.addDocument(document);
        writer.close();
        indexReader=IndexReader.open(directory);
        System.out.println("after add:"+indexReader.numDocs());
        indexReader.close();
        directory.close();
    }
}
