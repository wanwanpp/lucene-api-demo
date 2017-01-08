package com.wp.index;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestDelDocument {
    public static void main(String[] args) throws IOException {
        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);

        IndexReader indexReader = IndexReader.open(directory);
        System.out.println(indexReader.maxDoc());
        indexReader.deleteDocument(0);
        System.out.println(indexReader.deleteDocuments(new Term("id","2")));
        System.out.println(indexReader.numDocs());
        indexReader.close();
        directory.close();
    }
}
