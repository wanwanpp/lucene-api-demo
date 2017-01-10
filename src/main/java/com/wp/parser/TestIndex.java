package com.wp.parser;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;

public class TestIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String [] ids={"1","2","3","4"};
		String [] names={"张三","李逵","zhangsan ","zhangsun"};
		String [] addresses={"居住在北京","居住在南京","北京海淀","nanning"};
		String [] birthdays={"19820720","19840203","19770409","19830130"};
		Analyzer analyzer=new PaodingAnalyzer();
		String indexDir="F:\\IDEA\\Luence\\src\\main\\java\\com\\wp\\parser\\index";
		Directory dir=FSDirectory.getDirectory(indexDir);

		//true 表示创建或覆盖当前索引；false表示对当前索引进行追加
		//Default value is 128
		IndexWriter writer=new IndexWriter(dir,analyzer,true,IndexWriter.MaxFieldLength.LIMITED);

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
