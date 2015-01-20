package com.mycompany.app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
import net.sourceforge.jwbf.mediawiki.actions.queries.AllPageTitles;
import net.sourceforge.jwbf.mediawiki.actions.util.RedirectFilter;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

/**
 * Hello world!
 *
 */
public class App 
{
    final static MediaWikiBot BOT = new MediaWikiBot("https://he.wikisource.org/w/");
   static PrintWriter log;
    @SuppressWarnings({ "resource" })
	public static void main( String[] args ) throws FileNotFoundException, UnsupportedEncodingException
    {
    	 log = new PrintWriter("log.txt", "UTF-8");
       // MediaWikiBot wikiBot = new MediaWikiBot("https://he.wikisource.org/w/");
     //   Article article = wikiBot.getArticle("משתמשת:אור שפירא");     
    //    System.out.println(article.getText());
         System.out.print("username:");
         Scanner input = new Scanner(System.in);
         String username =input.nextLine();
        System.out.print("password:");
         input = new Scanner(System.in);
        String pswd = input.nextLine();
        if (username == "bot") username = "OrBoot";
       BOT.login(username, pswd);
        System.out.println("logged in");
        ohalot();
        log.close();
        System.out.println("end, read log for details");
      }
    private static void ohalot() {
        String query="תוספתא/אוהלות";
        AllPageTitles pages = searchList(query);
        applyChangesTo(pages); 
        log.println("end of ohalot");
    }
    private static AllPageTitles searchList(String query){
    	RedirectFilter rf = RedirectFilter.nonredirects;
    	 AllPageTitles pages = new AllPageTitles(BOT, null, query, rf);
    	 log.println("end of searchlist");
    	return pages;
    }
      private static void applyChangesTo(AllPageTitles pages) {
        Iterator<String> it = pages.iterator();

        while (it.hasNext()){
      
        	Article page = BOT.getArticle(it.next());
        	change(page);
        }
        log.println("end of applyCahnge to all pages");    	
    }
	private static void change(Article page) {
		String firstTitle = page.getTitle();
		String newTitle = firstTitle.replace("אוהלות", "אהלות");
		log.println("current title:"+ firstTitle+"\t new page:"+newTitle);
		Article newPage = BOT.getArticle(newTitle);
		if (newPage.getText().length()<1){
			log.println(newTitle+"is empty, creating new page");
			SimpleArticle newContent = new SimpleArticle(newTitle);
			newContent.setEditSummary("creating new page reidirected to [["+firstTitle+"]]");
			newContent.setText("#הפניה [["+firstTitle+"]]");
			BOT.writeContent(newContent);
			log.println(newContent.getText()+"created in"+newTitle);
		}
		else 
		{
			log.println(newTitle+"not empty, doing nothings");
		}
		log.println("end of change one page");
		// TODO continue here
		
	}
}
