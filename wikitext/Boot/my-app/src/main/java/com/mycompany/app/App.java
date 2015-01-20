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
    @SuppressWarnings({ "resource", "deprecation" })
	public static void main( String[] args ) throws FileNotFoundException, UnsupportedEncodingException
    {
    
        MediaWikiBot wikiBot = new MediaWikiBot("https://he.wikisource.org/w/");
     //   Article article = wikiBot.getArticle("משתמשת:אור שפירא");     
    //    System.out.println(article.getText());
        System.out.print("password:");
        Scanner input = new Scanner(System.in);
        String pswd = input.nextLine();
       wikiBot.login("OrBoot", pswd);
        System.out.println("logged in");
        String query="תוספתא/אוהלות";
        Iterator<String> = searchList(wikiBot, query);
      //  article.save();
      }
    private static Iterator<String> searchList(MediaWikiBot wikiBot, String query){
    	RedirectFilter rf = RedirectFilter.nonredirects;
    	 AllPageTitles pages = new AllPageTitles(wikiBot, null, query, rf);
    	 pages.iterator();
    	return pages.iterator();


    }
      static void applyChangesTo(Article article) {
        // edits the article...
    	
    }
}
