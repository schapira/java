package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
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
	//private static File file;
	private static boolean isSummary=false;
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
		this(title, text);
		pFile = new PrintWriter(newFile);
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
		this(title, text, newFile);
		addSummary(sum);
	}
	/*--------------------------------------------------*/
	public static void close(){
		log.println("end");
		log.close();
		pFile.close();
	}
	/**
	 * @throws Exception 
	 */
	public static void addSummary(String str) throws Exception{
		if (!isSummaryInFile()){
			pFile.print(START_OF_TITLE);
			pFile.println(SUMMARY_TITLE);
			pFile.println(str);
			pFile.println(EOF);
			isSummary = true;
			log.println("summary added");
		}
		else throw new Exception("There is already summary in the file");	
	}
	/**
	 * adds default summary to file
	 * @throws Exception
	 */
	public static void addSummary() throws Exception{
		addSummary("Added by bot"); 
		log.println("auto summary added");
	}
	/**
	 * return if there is summary in the file, this will return true for an empty summary
	 * @return true if had summary in the file
	 */
	public static boolean isSummaryInFile() {
		return isSummary;
	}
	/**
	 * @return current file of the class
	 */
	public static PrintWriter getFile() {
		return pFile;
	}
	/**
	 * change the file of the all class, use carefully
	 * @param file
	 * @param sum
	 * @throws FileNotFoundException, Exception
	 */
	public static void setFile(PrintWriter file, String sum) throws Exception {
		setFile(file);
		if (!isSummaryInFile()) addSummary(sum);
		log.println("file changed");
	}
	public static void setFile(PrintWriter file) throws Exception {
		pFile = new PrintWriter(file);
		ConvertToErelBotFormat.pFile = file;
		setFile(file,"Added by bot");		
	}
	/**
	 * add the current constant to the class file
	 */
	public void addToFile(){
		if (!isSummaryInFile())	try {addSummary();	} catch (Exception e) {e.printStackTrace();	}
		pFile.println(START_OF_TITLE+this.getTitle());
		pFile.println(this.getText());
		pFile.println(EOF);
		log.println("\""+this.getTitle()+ "\" added to the file");
	}
}