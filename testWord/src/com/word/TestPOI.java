package com.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.junit.Ignore;
import org.junit.Test;


public class TestPOI {
	
	@Test
	@Ignore
	public void testRead1(){
		
		File file=new File("d:/test.doc");
		try {
			FileInputStream fis=new FileInputStream(file);
			WordExtractor  wordExtractor=new WordExtractor(fis);
			System.out.println(wordExtractor.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	/**
	 * ·Ö¶Î¶Á²Ù×÷
	 */
	@Test 
	public void testPara(){
		
		File file=new File("d:/test.doc");
		try {
			FileInputStream fis=new FileInputStream(file);
			WordExtractor  wordExtractor=new WordExtractor(fis);
			String [] paras=wordExtractor.getParagraphText();
			for(int i=0;i<paras.length;i++){
				System.out.print("µÚ"+i+"¶Î-->");
				System.out.println(paras[i]);
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
