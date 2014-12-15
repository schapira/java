package com.mycompany.app;

import java.util.Iterator;
import java.util.Scanner;

import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.core.contentRep.SimpleArticle;
import net.sourceforge.jwbf.mediawiki.actions.queries.AllPageTitles;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings({ "resource", "deprecation" })
	public static void main( String[] args )
    {
    
        MediaWikiBot wikiBot = new MediaWikiBot("https://he.wikisource.org/w/");
        Article article = wikiBot.getArticle("משתמשת:אור שפירא");
        
        //System.out.println(article.getText());
        //System.out.print("password:");
        Scanner input = new Scanner(System.in);
        String pswd = input.nextLine();

        AllPageTitles pages = new AllPageTitles(wikiBot);
        Iterator<String> it = pages.iterator();
         while (it.hasNext())
        {
        	System.out.println(it.next());
        }
        wikiBot.login("OrBoot", pswd);
        
       //wikiBot.writeContent(new SimpleArticle("לךגכחךדלגחכד", "משתמשת:אור שפירא/דגכחלידלגחכי"));
        System.out.println("logged in");
      //  article.save();
      }

      static void applyChangesTo(Article article) {
        // edits the article...
    	
    }
}
