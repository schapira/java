package com.mycompany.app;

import java.io.File;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 
 * @author Or Schapira
 *
 */

public class DaatHitzoniim {
	/**
	 * Main method
	 * @param args
	 * @throws IOException 
	 */
	private static PrintWriter log;
	public static void main( String[] args ) throws Exception{
		init();
		doAllPages();
		close();	
	}
	private static void doAllPages() throws Exception  {
		//init of ConertToErelBotFormat
		ConvertToErelBotFormat.init(new File("output.txt"));	
		ConvertToErelBotFormat.addSummary("ספריים חיצוניים הזנה אוטומטית, אור שפירא");
		ConvertToErelBotFormat.addCategory("ספרים חיצוניים");
		//ConvertToErelBotFormat.addCategory("אור בוט");	
		File folder = new File("hasfarim");
		File files[] = folder.listFiles();
		//for (File file : files){
			 doOneBook(files[1]);
			 System.out.println(files[1].getPath());
			//page.addToFile();
		//}
		ConvertToErelBotFormat.close();
	}
	/**
	 * 
	 * @param file
	 * @return the current file in string format
	 * @throws Exception
	 */
	private static void doOneBook(File file) throws Exception  {
		Document doc = Jsoup.parse(file, "windows-1255", "");
		String book = doc.body().getElementById("name").ownText();
		Elements prakim = doc.body().getElementsByClass("perek");
		prakim.remove(0);//remove hakdama
		String perek="";
		String txt="";
		
		for (Element perekE:prakim)
		{
			 perek = perekE.text();
				perek = perek.replaceAll("\\[.*?\\] ?", "");
				perek = perek.replaceAll("פרק ", "");
				System.out.println(perekE.siblingElements().size());
				//System.out.println(perekE.siblingElements().first().text());
				for (Element content:perekE.siblingElements()) {
					txt+=content.text();
				}
				 System.out.println(txt);
				 //txt = removeNeedlessChars(txt);
				// txt = parsePsukim(txt);
				ConvertToErelBotFormat page = new ConvertToErelBotFormat(book	+ "/" + perek, txt); //TODO: adding the page content
				page.addToFile();

		}
	}

	/**
	 * Initialization of log
	 */
	private static void init () {
		try {log = new PrintWriter("logmain.txt", "UTF-8");
		} catch (Exception e) {	e.printStackTrace();	} 
	}
	/**
	 * close log print end to console
	 */
	private static void close (){
		log.close();
		System.out.println("end");
	}
	/**
	 * cleaning string from ",.-[...]"
	 * convert (..) to titles
	 * @param infile
	 * @return cleaned string
	 */
	private static String removeNeedlessChars (String infile){
		String str=infile.substring(0);
		log.print("removeNeedlessWords");
		str = str.replaceAll(",", "");
		//str.replaceAll("\\. \\(([^)]*)\\)", ". \n==$1==\n"); //ANSWER from stackoverflow http://stackoverflow.com/questions/29143747/replace-brackets-only-when-they-are-after-a-dot
		str = str.replaceAll("\\.", "");
		str = str.replaceAll("'", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll("-", " ");
		str = str.replaceAll("\\[.*?\\] ?", "");
		//str = str.replaceAll(". \\(\\.\\.","");

//		str = str.replaceAll("\\(", "\n==");
	//	str = str.replaceAll("\\) ", "==\n");
		log.println(": done");
		return str;
	}
	private static String parsePsukim(String inTxt){
		String str = inTxt;
		//str.replaceAll("\\(([^)]*)\\)", ". \n==$1==\n"); 
		return str;
	}
}
