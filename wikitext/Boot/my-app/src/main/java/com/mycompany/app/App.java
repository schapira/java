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
    @SuppressWarnings({ "resource" })
	public static void main( String[] args ) throws FileNotFoundException, UnsupportedEncodingException
    {
    
       // MediaWikiBot wikiBot = new MediaWikiBot("https://he.wikisource.org/w/");
     //   Article article = wikiBot.getArticle("משתמשת:אור שפירא");     
    //    System.out.println(article.getText());
        System.out.print("password:");
        Scanner input = new Scanner(System.in);
        String pswd = input.nextLine();
       BOT.login("OrBoot", pswd);
        System.out.println("logged in");
        String query="תוספתא/אוהלות";
        AllPageTitles pages = searchList(query);
        applyChangesTo(pages);
        
      //  article.save();
      }
    private static AllPageTitles searchList(String query){
    	RedirectFilter rf = RedirectFilter.nonredirects;
    	 AllPageTitles pages = new AllPageTitles(BOT, null, query, rf);
    	return pages;


    }
      private static void applyChangesTo(AllPageTitles pages) {
        Iterator<String> it = pages.iterator();
        int i = 0; //run only once
        while (it.hasNext()&&(i<1)){
        	i++;
        	Article page = BOT.getArticle(it.next());
        	change(page);
        }
    	
    }
	private static void change(Article page) {
		String newTitle = page.getTitle().replace("אוהלות", "אהלות");
		Article moveto = BOT.getArticle(newTitle);
		// TODO continue here
		
	}
}
