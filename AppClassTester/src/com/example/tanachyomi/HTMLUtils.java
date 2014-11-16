package com.example.tanachyomi;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;


public class HTMLUtils 
{
  public final static void main(String[] args) throws IOException// throws Exception
  {
	  File input = new File("src/com/example/tanachyomi/b01_ch01.html");
	  Document doc = Jsoup.parse(input, "windows-1255", "");
	  PrintWriter writer = new PrintWriter("file.txt", "UTF-8");
	  doc = cleaning(doc);
	  doc.select("a").remove();
	  doc.select("meta").remove();	  
	  doc.select("link").remove();
	  doc.select("xml").remove();	
	  doc.select("style").remove();	
	  doc.select("link").remove();	
	  doc.select("link").remove();	
	  doc.select("link").remove();	
	  doc.select("link").remove();	
	  
	  
	    for (Element element : doc.select("*")) 
	    {
	        if (!element.hasText()||(element.text()=="<o:p>&nbsp;</o:p>"))
	        {
	            element.remove();
	        }
	    }
	    String prev="";
	    String current="";
	    String output = "";
	    for (Element element : doc.select("*"))
	    {  	
	    	current = element.text();	
	    	//System.out.println("current"+current);
	    	//System.out.println("prev"+prev);
	    	if (!current.equals(prev))
	    	{
	    		output+= current+"\n";
	    		prev = current;
	    	}
	    }
	    System.out.println(output);
	  
	  


	  writer.println(doc.body());
	  writer.close();
	  //System.out.println(doc.text());
  }
  private static Document cleaning(Document doc )
  {

NodeTraversor traversor  = new NodeTraversor(new NodeVisitor() {
	
  @Override
  public void tail(Node node, int depth) {
    if (node instanceof Element) {
        Element e = (Element) node;
        e.removeAttr("class");
        e.removeAttr("bgcolor");
        e.removeAttr("lang");
        e.removeAttr("dir");
        e.removeAttr("width");
        e.removeAttr("height");
        e.removeAttr("style");
        e.removeAttr("style");
        e.removeAttr("style");
        e.removeAttr("style");
        
        
    }
  }



@Override
public void head(Node node, int depth) {
	// TODO Auto-generated method stub
	
}


});

traversor.traverse(doc.body());
return doc;

  }
}