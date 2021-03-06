package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
	private final static String CATEGORY_START = "[[קטגוריה:";
	private final static String END_OF_LINK = "]]";
	/*-----------------------------------------------	*/
	//STATICS
	private static PrintWriter log;
	private static boolean isSummary=false;
	private static PrintWriter pFile;
	private static ArrayList<String> categories;
	private static boolean init =false;
	/*----------------------------------------------*/
	public static void init(File file){
		init = true;
		try {pFile = new PrintWriter(file);} catch (FileNotFoundException e) {	e.printStackTrace();	}
		if(log==null)
			try {log = new PrintWriter("ConvertToErelBotFormat.log.txt", "UTF-8");
			}catch (Exception e) {	e.printStackTrace();}
		categories = new ArrayList<String>();
	}
	/**
	 * basic constructor
	 * @param title
	 * @param text
	 */
	public ConvertToErelBotFormat(String title, String text)  {
		super(title);
		if (!init) init(new File("output.txt"));
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
		if (!isSummary){
		addSummary(sum);
		}
		else log.println("there is already summery in file");
	}
	/**
	 * close all open connections
	 */
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
	/**
	 * set the output file with auto summary
	 * @param file
	 * @throws Exception
	 */
	public static void setFile(PrintWriter file) throws Exception {
		if (pFile!=null) pFile.close();
		pFile = new PrintWriter(file);
	}
	/**
	 * add the current constant to the class file
	 */
	public void addToFile(){
		if (!isSummaryInFile())	try {addSummary();	} catch (Exception e) {e.printStackTrace();	}
		//title
		pFile.println(START_OF_TITLE+this.getTitle());
		//body
		pFile.println(this.getText());
		//category 
		if (categories!=null  && !categories.isEmpty()) {
			for (String cat : categories)
			{
			pFile.println(CATEGORY_START + cat + END_OF_LINK);
			}
		}
		//end of file message
		pFile.println(EOF);
		log.println("\""+this.getTitle()+ "\" added to the file");
	}
	public static ArrayList<String> getCategories() {
		return categories;
	}
	public static void addCategory(String category) {
		ConvertToErelBotFormat.categories.add(category);
	}
}