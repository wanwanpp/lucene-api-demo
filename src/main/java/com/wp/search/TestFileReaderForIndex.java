package com.wp.search;

import com.wp.index.TestIndex;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 王萍 on 2017/1/8 0008.
 */
public class TestFileReaderForIndex {

    public static void main(String[] args) throws IOException {
        File file = new File("F:\\IDEA\\Luence\\src\\main\\java\\com\\wp\\data.txt");
        FileReader fileReader = new FileReader(file);
        char[] chs = new char[60000];
        fileReader.read(chs);
        String strTemp = new String(chs);
        String[] strings = strTemp.split("Database: Compendex");
        System.out.println(strings.length);

        for (String string:strings){
            string=string.trim();
        }

        Directory directory = FSDirectory.getDirectory(TestIndex.INDEX_DIR);
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriter writer = new IndexWriter(directory,analyzer,false, IndexWriter.MaxFieldLength.LIMITED);
        for (String string:strings){
            Document document = new Document();
            document.add(new Field("contents",string, Field.Store.YES, Field.Index.ANALYZED));
            writer.addDocument(document);
        }
        writer.optimize();
        writer.close();
        directory.close();
        System.out.println("Index OK!");
    }
}
