package com.mycompany.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
/**
 * 
 * @author Or Schapira
 *
 */
@SuppressWarnings("serial")
public class ConvertToErelBotFormat extends SimpleArticle {

	//FINALES
	private final static String EOF = "סוףקובץ";
	private final static String SUMMARY_TITLE = "תקציר";
	private final static String START_OF_TITLE = "#####";
//-----------------------------------------------	
	//STATICS
	private static PrintWriter log;
	private static File file;

	private static PrintWriter pFile;
/*----------------------------------------------*/
	/**
	 * basic constructor
	 * @param title
	 * @param text
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	public ConvertToErelBotFormat(String title, String text)  {
		super(title);
		if(log==null)
			try {log = new PrintWriter("ConvertToErelBotFormat.log.txt", "UTF-8");
			}catch (Exception e) {	e.printStackTrace();}
		this.setText(text);
	}
	/**
	 * constructor with file, this may change the file the all class is working on, use carefully
	 * @param title
	 * @param text
	 * @param newFile
	 * @throws Exception 
	 */
	public ConvertToErelBotFormat(String title, String text, File newFile) throws Exception {
		this(title, text, newFile,"Added by bot");
	}
	/**
	 * constructor with file, this may change the file the all class is working on, use carefully
	 * @param title
	 * @param text
	 * @param newFile
	 * @param sum
	 * @throws Exception 
	 */
	public ConvertToErelBotFormat(String title, String text, File newFile,String sum) throws Exception {
		this(title, text);
		file = newFile;
		pFile = new PrintWriter(file);
		addSummary(sum);
	}
	/*--------------------------------------------------*/
	public void end(){
		log.close();
	}
	/**
	 * TODO QA
	 * @throws Exception 
	 */
	public static void addSummary(String str) throws Exception{
		if (!isSummaryInFile()){
			pFile.print(START_OF_TITLE);
			pFile.println(SUMMARY_TITLE);
			pFile.println(str);
			pFile.println(EOF);
		}
		else throw new Exception("There is already summary in the file");	
	}
	/**
	 * adds default summary to file
	 * @throws Exception
	 */
	public static void addSummary() throws Exception{
		addSummary("Added by bot"); 
	}
	/**
	 * TODO QA this method
	 * return if there is summary in the file, this will return true for an empty summary
	 * @return true if had summary in the file
	 */
	public static boolean isSummaryInFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			if (line == null) {
				br.close();
				return false;
			}
			if (line.startsWith(START_OF_TITLE) && line.contains(SUMMARY_TITLE)){
				line = br.readLine();
				line = br.readLine();
				if ( line.contains(EOF)){
					br.close();
					return true;
				}
			}
			br.close();
		} catch (Exception e) 	{e.printStackTrace();}
		return false;
	}
	/**
	 * 
	 * @return current file of the class
	 */
	public static File getFile() {
		return file;
	}
	/**
	 * change the file of the all class, use carefully
	 * @param file
	 * @throws FileNotFoundException 
	 */
	public static void setFile(File file, String sum) throws Exception {
		pFile = new PrintWriter(file);
		ConvertToErelBotFormat.file = file;
		addSummary(sum);
	}
	public static void setFile(File file) throws Exception {
		setFile(file,"Added by bot");
	}

public void addToFile(){
	/*
	 * TODO
	 * this should add the current constant to the class file
	 */
}

	

	

}
