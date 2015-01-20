
package com.example.tanachyomi;

import java.util.Calendar;
import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;


/**
 * @author or
 *
 */
public class NachCalendar extends JewishCalendar 
{
	private boolean doubleRuth = false;
	private boolean doubleSongs= false;
	private  boolean doubleJoshua= false;
	private boolean doubleJeremiah= false;
    public NachCalendar(NachCalendar nachCalendar)
    {
    	super(nachCalendar.getJewishYear(), nachCalendar.getJewishMonth(),nachCalendar.getJewishDayOfMonth(),nachCalendar.getInIsrael());
    	init();
	}
	public boolean isYomChol()
    {
    	if (getDayOfWeek() == 7)  
    			{
    		 return false; //Shabbath
    			}
    	int yomtov = getYomTovIndex();
    	switch (yomtov)
    	{
    	case (-1): return true;
    	case (JewishCalendar.PESACH):
    	case (JewishCalendar.SHAVUOS):
    	case (JewishCalendar.SUCCOS):
    	case (JewishCalendar.ROSH_HASHANA):
    	case (JewishCalendar.YOM_KIPPUR):
    	case (JewishCalendar.PURIM):
    	case (JewishCalendar.YOM_HAATZMAUT):
    	case (JewishCalendar.SHEMINI_ATZERES):
    	case (JewishCalendar.TISHA_BEAV):return false;
    	default: return true; 	
    	}
    }
    	public int getYemeCholNum()
    	{
    		NachCalendar temp = new NachCalendar(this);
    		temp.setJewishDayOfMonth(1);
    		temp.setJewishMonth(7);
    		int year = temp.getJewishYear();
    		int sum = 0;
    		while (temp.getJewishYear() == year)
    		{
    			if (temp.isYomChol()) sum++;
    			temp.forward(); 		
    		}
    		return sum;
    	}
		public NachCalendar() {
			super();
			init();
			setJewishDayOfMonth(1);
			setJewishMonth(7);
		} 
		private void init()
		{
			setInIsrael(true);
			setUseModernHolidays(true);
			HebrewDateFormatter formater = new HebrewDateFormatter();
			String kviut = formater.getFormattedKviah(getJewishYear());
			switch (kviut)
			{
			//Adding 4 days
			case "זשה": doubleJoshua = true;
			//Adding 3 days
			case "זשג":doubleJeremiah = true;
			//Adding 2 days
			case "השג":
			case "בשז":
			case "השא":
			case "זחא":
			case "זחג": doubleSongs = true;
			//Adding 1 days
			case "החא":
			case "בשה":
			case "גכז":
			case "הכז":
			case "בחג": doubleRuth = true;
			//Not adding any day
			case "בחה":			
			case "גכה":
			default:break;
			}
		}
		@Override
		public void setDate(Calendar calendar) 
		{
			super.setDate(calendar);
			init();
		}
		@Override
		public void setGregorianDate(int year, int month, int dayOfMonth) 
		{
				super.setGregorianDate(year, month, dayOfMonth);
				init();
		}
		@Override
		public void setJewishDate(int year, int month, int dayOfMonth,
				int hours, int minutes, int chalakim) 
		{
			super.setJewishDate(year, month, dayOfMonth, hours, minutes, chalakim);
			init();
		}
		@Override
		public void setGregorianYear(int year) 
		{
				super.setGregorianYear(year);
				init();
		}

		public boolean isDoubleRuth() 
		{
			return doubleRuth;
		}
		public boolean isDoubleSongs()
		{
			return doubleSongs;
		}
		public boolean isDoubleJoshua() 
		{
			return doubleJoshua;
		}
		public boolean isDoubleJeremiah()
		{
			return doubleJeremiah;
		}
}