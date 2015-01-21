package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
		File inputFile = new File("hayovlim/1.html");
		Document doc = Jsoup.parse(inputFile, "windows-1255", "");
		String output = doc.body().text();
		output = removeNeedlessWords(output);
		ConvertToErelBotFormat page = new ConvertToErelBotFormat("title", output, new File("output.txt"), "ספר יובלים - הזנה אוטומטית, אור שפירא");
		page.addToFile();
		ConvertToErelBotFormat.close();
		close();
	}
	/**
	 * Initialization of log
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	private static void init () throws FileNotFoundException, UnsupportedEncodingException{
		log = new PrintWriter("log.txt", "UTF-8");
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
	private static String removeNeedlessWords (String infile){
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
