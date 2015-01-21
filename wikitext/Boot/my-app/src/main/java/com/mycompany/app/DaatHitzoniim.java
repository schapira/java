package com.mycompany.app;

import java.io.File;
import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
		File file = new File("hayovlim/1.html");
		String body = doOnePage(file);
		String fileName =file.getName();
		fileName = fileName.substring(0, fileName.lastIndexOf('.'));
		System.out.println(fileName);
		ConvertToErelBotFormat page = new ConvertToErelBotFormat(fileName, body, new File("output.txt"), "ספר יובלים - הזנה אוטומטית, אור שפירא");
		page.addToFile();
		ConvertToErelBotFormat.close();
	}
	/**
	 * 
	 * @param file
	 * @return the current file in string format
	 * @throws Exception
	 */
	private static String doOnePage(File file) throws Exception  {
		Document doc = Jsoup.parse(file, "windows-1255", "");
		String output = doc.body().text();
		output = removeNeedlessChars(output);
		return output;
	}
	/**
	 * Initialization of log
	 */
	private static void init () {
		try {log = new PrintWriter("log.txt", "UTF-8");
		} catch (Exception e) {	e.printStackTrace();	} 
	}
	/**
	 * close log print end to console
	 */
	private static void close (){
		log.println("end");
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
		str = str.replaceAll("\\.", "");
		str = str.replaceAll("'", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll("-", " ");
		str = str.replaceAll("\\[.*?\\] ?", "");
		str = str.replaceAll("\\(", "\n==");
		str = str.replaceAll("\\)", "==\n");
		log.println(": done");
		return str;

	}


}
