package com.html;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import org.htmlparser.visitors.ObjectFindingVisitor;
import org.junit.Ignore;
import org.junit.Test;


public class TestHtmlParser {
	
	 /**
	  * 
	  * 测试ObjectFindVisitor的用法
	  */

	@Test
	@Ignore
	public void testImageVisitor() {
	        try {
	            ImageTag imgLink;
	            ObjectFindingVisitor visitor = new ObjectFindingVisitor(ImageTag.class);
	            Parser parser = new Parser();
	            parser.setURL("http://www.baidu.com");
	            parser.setEncoding(parser.getEncoding());
	            parser.visitAllNodesWith(visitor);
	            Node[] nodes = visitor.getTags();
	           
	            for (int i = 0; i < nodes.length; i++) {
	                imgLink = (ImageTag) nodes[i];
	              StringBuilder sb=new StringBuilder();
	               sb.append(" ImageURL = "+ imgLink.getImageURL());
	               sb.append("---- ImageLocation = "
	                + imgLink.extractImageLocn());
	               sb.append("--- SRC = "
	                + imgLink.getAttribute("SRC"));
	               System.out.println(sb.toString());
	               
	            }
	           
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	/**
	 * 测试NodeVisitor的用法，遍历所有节点
	 */
    @Test
    @Ignore
    public void testVisitorAll() {
        try {
            Parser parser = new Parser();
            parser.setURL("http://www.baidu.com");
            parser.setEncoding(parser.getEncoding());
            NodeVisitor visitor=new MyNodeVisitor();
            parser.visitAllNodesWith(visitor);
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    
    
    /*
     * 测试NodeClassFilter用法
     */
     @Test
     @Ignore
     public void testLinkTag() {
         try {

             NodeFilter filter = new NodeClassFilter(LinkTag.class);
             Parser parser = new Parser();
             parser.setURL("http://www.baidu.com");
             parser.setEncoding(parser.getEncoding());
             NodeList list = parser.extractAllNodesThatMatch(filter);
             for (int i = 0; i < list.size(); i++) {
                 LinkTag node = (LinkTag) list.elementAt(i);
                 System.out.println("Link is :" + node.extractLink());
             }
         } catch (Exception e) {
             e.printStackTrace();
         }

     }

     
     /*
      * 测试TagNameFilter用法
      */
      @Test
      @Ignore
      public void testNodeFilter() {
          try {
              NodeFilter filter = new TagNameFilter("IMG");
              Parser parser = new Parser();
              parser.setURL("http://www.baidu.com");
              parser.setEncoding(parser.getEncoding());
              NodeList list = parser.extractAllNodesThatMatch(filter);
              for (int i = 0; i < list.size(); i++) {
             	 System.out.println(" " + list.elementAt(i).toHtml());
              }
          } catch (Exception e) {
              e.printStackTrace();
          }

      }
      
      /*
       * 测试OrFilter的用法
       */
       @Test
//       @Ignore
       public void testOrFilter() {
           NodeFilter inputFilter = new NodeClassFilter(InputTag.class);
           NodeFilter selectFilter = new NodeClassFilter(SelectTag.class);
           Parser myParser;
           NodeList nodeList = null;
       
           try {
               Parser parser = new Parser();
               parser
               .setInputHTML("<head><title>OrFilter Test</title>"
               + "<link href=http://www.baidu.com/test01/css.css’ text=’text/css’ rel=’stylesheet’ />"
               + "<link href=http://www.baidu.com/test02/css.css’ text=’text/css’ rel=’stylesheet’ />"
               + "</head>"
               + "<body>"
               + "<input type=’text’ value=’text1′ name=’text1′/>"
               + "<input type=’text’ value=’text2′ name=’text2′/>"
               + "<select><option id=’1′>1</option><option id=’2′>2</option><option id=’3′>3</option></select>"
               + "<a href='http://www.baidu.com/'>baidu.com</a>"
               + "</body>");
           
               parser.setEncoding(parser.getEncoding());
               OrFilter lastFilter = new OrFilter();
               lastFilter.setPredicates(new NodeFilter[] { selectFilter,
               inputFilter });
               nodeList = parser.parse(lastFilter);
               for (int i = 0; i <= nodeList.size(); i++) {
                   if (nodeList.elementAt(i) instanceof InputTag) {
                       InputTag tag = (InputTag) nodeList.elementAt(i);
                       System.out.println("OrFilter tag name is :" + tag.getTagName()
                       + " ,tag value is:" + tag.getAttribute("value"));
                   }
                   if (nodeList.elementAt(i) instanceof SelectTag) {
                           SelectTag tag = (SelectTag) nodeList.elementAt(i);
                           NodeList list = tag.getChildren();
                   
                       for (int j = 0; j < list.size(); j++) {
                           OptionTag option = (OptionTag) list.elementAt(j);
                           System.out.println("OrFilter Option"
                           + option.getOptionText());
                       }
                   
                   }
               }
       
           } catch (ParserException e) {
           e.printStackTrace();
           }
       } 

}
class MyNodeVisitor extends NodeVisitor  {
    public void visitTag(Tag tag) {
        System.out.println("Tag name is :"
        + tag.getTagName() + "--- Class is :"
        + tag.getClass()+"---"+tag.getText());
    }
}