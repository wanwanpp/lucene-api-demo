package com.wp.index;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestUnDeleteDocument {

    public static void main(String[] args) throws IOException {

        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);

        IndexReader indexReader = IndexReader.open(directory);
        System.out.println("before delete:" + indexReader.numDocs());

        //执行删除方法会产生del文件
//        indexReader.deleteDocuments(new Term("id","1"));
        //执行此方法，可以删除index中产生的del文件
        indexReader.undeleteAll();

        System.out.println("after delete:" + indexReader.numDocs());
        indexReader.close();
        directory.close();
    }
}
