
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class Tosefta 
{
  public final static void main(String[] args) throws IOException// throws Exception
  {
	  File input = new File("htmls/f44.html"); ////Tosefta/htmls/f16.html

	  Document doc = Jsoup.parse(input, "windows-1255", "");
	  PrintWriter writer = new PrintWriter("file.txt", "UTF-8");


	  String str = cleaning(doc.body());

	  writer.println(str);
	  writer.close();
	  System.out.println(doc.text());
  }
  private static String cleaning(Element element)
  {

     Elements h2s = element.getElementsByTag("h2");
	 String koteret;
	 String perek;
	 String prePerek="";
	 char next;
	 for (Element h2:h2s)
	 {
		 koteret = "#####תוספתא/"+h2.text();
		 koteret = koteret.replaceAll("מסכת ", "");
		 koteret = koteret.replaceAll("פרק ", "");
	//	 koteret = koteret.replaceAll("טבול ", "טבול_");
		 koteret = koteret.replace(' ', '/');
		 perek = koteret.substring(koteret.length()-1, koteret.length());
		 next = (char) (perek.toCharArray()[0] +1);
		 h2.text(koteret);
		 h2.append("<div>{{תוספתא פרק|סנהדרין|"+perek+"|"+prePerek+"|"+next+"}}");
		 prePerek = perek;
	 }

	 Elements bs = element.getElementsByTag("b");
	for (Element b:bs)
	{
	//	b.text(b.text().replaceAll(",", ""));
		String[] arr = b.text().split(",");
		//System.out.println();
		b.text("==הלכה"+" "+arr[1]+"==");		 
	}

	  String str = element.getElementsByTag("div").first().html().toString();
	  str = str.replaceAll("/עורלה", "/ערלה");
	  str = str.replaceAll("</b>&nbsp; ", "\n");
      str = str.replaceAll("<div>", " ");
      str = str.replaceAll("</div>", "");
	  str = str.replaceAll("<p><b>", "");
	  str = str.replaceAll("</p>", "");
	  str = str.replaceAll("&nbsp", "");
	  str = str.replaceAll("<p>", "");
	  str = str.replaceAll("&lt;", ""); //
	  str = str.replaceAll("<h2>", "סוףקובץ\n");
	  str = str.replaceAll("</h2>","\n");
	  str = str.replaceAll("&gt;", ""); 
	  str = str.replaceAll(";", ""); 
	  str = str.replaceAll("<br>", ""); 
	//  str = str.replaceAll("\\(.*?\\)","");
	//  str = str.replaceAll("\\[.*?\\]","");
	  str = str.replaceAll("<p></p> " ,"");
	  str = str.replaceAll("  ", " "); 
	  str = "#####תקציר \n הוספת התוספתא על ידי בוט\n	"+str;
	  str = str.replaceAll("\n ", "\n");
	  str = str.replaceAll("\n\n", "\n");
	  str = str.replaceAll("# ", "#");
	  //
	  str = str.replaceAll("אע\"פ", "אף על פי");
	  str = str +"\nסוףקובץ\n";  
	  return str;
	
  }
}

