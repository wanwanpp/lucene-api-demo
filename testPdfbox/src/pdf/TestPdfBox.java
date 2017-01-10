package pdf;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import java.io.*;

/**
 * Created by Õı∆º on 2017/1/10 0010.
 */
public class TestPdfBox {

    public static void main(String[] args) throws IOException {
        PDDocument document = PDDocument.load("C:/Users/wang0/Desktop/Õı∆º.pdf");
        int pagenumber = document.getNumberOfPages();
        System.out.println("pages -- "+pagenumber);

//        FileOutputStream fos = new FileOutputStream("F:\\IDEA\\Luence\\testPdfbox\\src\\pdf\\export.txt");
//        Writer writer = new OutputStreamWriter(fos,"GBK");
        Writer writer = new OutputStreamWriter(System.out,"GBK");
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(false);
//        stripper.setStartPage(1);
//        stripper.setEndPage(4);
        stripper.writeText(document,writer);

        writer.close();
        document.close();
    }
}
