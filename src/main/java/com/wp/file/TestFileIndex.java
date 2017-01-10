package com.wp.file;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;

/**
 * Created by Õı∆º on 2017/1/10 0010.
 */
public class TestFileIndex {

    public static final String DataDir = "F:\\IDEA\\Luence\\txtData";
    public static final String IndexDir = "F:\\IDEA\\Luence\\src\\main\\java\\com\\wp\\data\\index";

    public static void main(String[] args) throws IOException {
        File[] files = new File(DataDir).listFiles();
        System.out.println(files.length);

        Analyzer analyzer = new PaodingAnalyzer();
        Directory directory = FSDirectory.getDirectory(IndexDir);
        IndexWriter writer = new IndexWriter(directory,analyzer,true, IndexWriter.MaxFieldLength.LIMITED);

        for (int i =0;i<files.length;i++){
            StringBuffer stringBuffer = new StringBuffer();
            FileInputStream inputStream = new FileInputStream(files[i].getCanonicalPath());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            String line = bufferedReader.readLine();
            while (line!=null){
                stringBuffer.append(line);
                stringBuffer.append("\n");
                line=bufferedReader.readLine();
            }

            Document document = new Document();
            document.add(new Field("fileName",files[i].getName(), Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("contents",stringBuffer.toString(), Field.Store.YES, Field.Index.ANALYZED));
            writer.addDocument(document);
        }

        writer.close();
        directory.close();
    }
}
