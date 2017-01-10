package com.html;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class TestJTidy {

	/**
	 * @param args
	 * @throws Exception 
	 */

    /**
     * JTidy的作用是讲html转为规范的xml文档
     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream(new File("F:\\IDEA\\Luence\\testHtml\\src\\com\\html\\out\\baidu.html"));
		InputStreamReader isr=new InputStreamReader(fis,"GBK");
		
        FileOutputStream fos=new FileOutputStream(new File("F:\\IDEA\\Luence\\testHtml\\src\\com\\html\\out\\baidu1.xml"));
        Tidy tidy=new Tidy();
        //使用xml标签规范
        tidy.setXmlTags(true);

        tidy.setMakeClean(true);
        Document doc=tidy.parseDOM(isr, null);
        tidy.pprint(doc, fos);
        fos.close();
        fis.close();
        isr.close();
	}

}
