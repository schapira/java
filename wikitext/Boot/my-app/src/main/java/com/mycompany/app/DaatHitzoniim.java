package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DaatHitzoniim {
	/**
	 * Main method
	 * @param args
	 * @throws IOException 
	 */
	   static PrintWriter log;
	public static void main( String[] args ) throws IOException{
		init();
		File inputFile = new File("hayovlim/1.html");
		  Document doc = Jsoup.parse(inputFile, "windows-1255", "");
		  PrintWriter writer = new PrintWriter("file.txt", "UTF-8");
		  String output = doc.body().text();
		  output = removeNeedlessWords(output);
		  writer.println(output);
		  writer.close();
		  finish();
	}
	private static void init () throws FileNotFoundException, UnsupportedEncodingException{
		  log = new PrintWriter("log.txt", "UTF-8");
	}
	private static void finish (){
		log.close();
		System.out.println("end");
	}
	private static String removeNeedlessWords (String infile){
		String str=infile.substring(0);
		//TODO continue here
		str = str.replaceAll(",", "");
		str = str.replaceAll("\\.", "");
		str = str.replaceAll("'", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll("-", " ");
		str = str.replaceAll("\\[.*?\\] ?", "");
		str = str.replaceAll("\\(.*?\\) ?", "");

		return str;
		
	}
	

}
