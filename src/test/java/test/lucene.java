//package test;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.filefilter.TrueFileFilter;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.analysis.util.CharArraySet;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
//import org.junit.Test;
//
//import java.io.File;
//import java.util.Collection;
//
///**
// * Created by 王萍 on 2017/1/8 0008.
//使用4.9.0版本
// */
//public class lucene {
//
//    public static final String DATA_DIR="F:\\IDEA\\Luence\\txtData";
//
//    public static final String INDEX_DIR="F:\\IDEA\\Luence\\index";
//
//    @Test
//    public void createIndex(){
//        try {
//            //4. 通过CharArraySet可以向分词中追加一些停止词（即排除检索的词）
//            CharArraySet arrSet = new CharArraySet(Version.LUCENE_4_9, 0, false);
//            //3. Analyzer 用于对数据源进行分词
//            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9, arrSet);
//            //2. IndexWriter的配置信息都存放在IndexWriterConfig中
//            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9,analyzer);
//            // OpenMode.CREATE_OR_APPEND 指定，该创建索引是可以在以后通过追加的方式向里面添加内容
//            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
//
//            //1. 创建索引的入口，创建索引必须用IndexWriter进行创建或者追加
//            Directory dir = FSDirectory.open(new File(INDEX_DIR));
//            IndexWriter writer = new IndexWriter(dir,config);
//
//            File dataDir = new File(DATA_DIR);
//            //5.得到数据源中所有的文件
//            Collection<File> files = FileUtils.listFiles(dataDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
//            for(File file : files){
//                //6. 通过向Writer追加Document的方式添加内容
//                Document doc = new Document();
//                doc.add(new StringField("filename",file.getName(), Field.Store.YES));
//                String content = FileUtils.readFileToString(file);
//                doc.add(new TextField("content",content, Field.Store.YES));
//                writer.addDocument(doc);
//            }
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void search(){
//        try {
//            String keyword = "wangping";
//            // 在这里进行检索的时候，需要加载的目录就是创建索引的目录，创建索引以后，那些原数据源在Lucene上就暂时用不到了
//            Directory directory = FSDirectory.open(new File(INDEX_DIR));
//            IndexReader reader = DirectoryReader.open(directory);
//            // IndexSearcher 是Lucene的检索的入口点，所有检索都从这里入口
//            IndexSearcher searcher = new IndexSearcher(reader);
//            // 通过analyzer对用户输入的词进行分词
//            StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
//            // 构建检索条件
//            QueryParser parser = new QueryParser(Version.LUCENE_4_9, "content",analyzer);
//            Query query = parser.parse(keyword);
//            // 最后使用searcher.search检索，search方法的参数很多，还可以根据需求，取出相应的条数
//            TopDocs topDocs = searcher.search(query, 20);
//            // topDocs.totalHits 返回的是所有检索到记录的条数的总和
//            ScoreDoc[] docs = topDocs.scoreDocs;
//            System.out.println("关键词\" "+keyword+" \"共检索到 "+topDocs.totalHits+" 条相关的记录");
//            System.out.println("被检索到记录，他们分别放在以下的文件中：");
//            for(ScoreDoc doc : docs){
//                int docId = doc.doc;
//                Document document = reader.document(docId);
//                System.out.println(document.get("filename"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
