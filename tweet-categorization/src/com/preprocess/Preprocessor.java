package com.preprocess;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Preprocessor {
	
	public Map <String , Integer> tweet = new HashMap <String , Integer>();
	
	/*
	 * 
	 * Removes links, url's etc.
	 */
	
	public String removeLinks(String val)
	{
		String res = new String("");
		StringTokenizer token = new StringTokenizer(val);
		while(token.hasMoreElements())
		{
			String str = (String) token.nextElement();
			CharSequence ch = "http://";
			CharSequence ch1 = "www.";
			CharSequence ch2 = "https://";
			CharSequence ch3 = "ssh://";
			CharSequence ch4 = "ftp://";
			if( !str.contains(ch) )
			{
				if( !str.contains(ch1) )
				{
					if( !str.contains(ch2) )
					{
						if( !str.contains(ch3) )
						{
							if( !str.contains(ch4) )
							{
								res = res + str + " ";
							}
						}
					}
				}
			}
	
	/* 
	 * 		if( !str.contains(ch) || !str.contains(ch1) || !str.contains(ch2) || !str.contains(ch3) || !str.contains(ch4))
	 *		res = res + str + " ";
	 */
		}
		return res;
	}
	
	/* 
	 * removes twitter user name.
	 */
	
	public String removeUsername(String val)
	{
		String res = new String("");
		StringTokenizer token = new StringTokenizer(val);
		while(token.hasMoreElements())
			{
				String str = (String) token.nextElement();
				CharSequence ch = "@";
				if(!str.contains(ch))
					res = res + str + " ";
	   /*	
		* if(!str.startsWith("@"))
		* 	{
		*		if(!str.startsWith("\"@"))
		*			res = res + str + " ";
		*	}
		*
		* System.out.println(token.nextElement()+"\n");
		*/
			}
		return res;
	}
	
	/*
	 * 
	 * Removing re-tweet.
	 */
	
	public String removeReTweet(String val)
	{
		String res = new String("");
			
		return res;
	}
	
	/*
	 * 
	 * Remove # from hash tag.
	 */
	
	public String removeHash(String val)
	{
		String res = new String("");
		StringTokenizer token = new StringTokenizer(val);
		while(token.hasMoreElements())
		{
			String str = (String) token.nextElement();
			if(str.startsWith("#"))
			{
				res = res + str.substring(1,(int)str.length()) + " ";
			}
			else
				res = res + str + " ";
		}
		return res;
	}
	
	/*
	 * 
	 * Remove Emoticons from tweets
	 */
	
	public String removeEmoticons(String result)
	{
		result = result.replaceAll(":[)]", "");
		result = result.replaceAll(";[)]", "");
		result = result.replaceAll(":-[)]", "");
		result = result.replaceAll(";-[)]", "");
		result = result.replaceAll(":d", "");
		result = result.replaceAll(";d", "");
		result = result.replaceAll("=[)]", "");
		result = result.replaceAll("\\^_\\^", "");
		result = result.replaceAll(":[(]", "");
		result = result.replaceAll(":-[(]", "");
		return result;
	}
	
	public static void main(String [] args) throws Exception
	{
		int tweetCount = 0;
		Preprocessor p = new Preprocessor();
		FileReader politics = new FileReader("politics.txt");
		BufferedReader br = new BufferedReader(politics);
		String line;
		while((line = br.readLine()) != null)
		{
			tweetCount += 1;
			line = p.removeUsername(line);
			line = p.removeLinks(line);
			line = p.removeHash(line);
			line = p.removeEmoticons(line);
			if(!line.startsWith("RT"))
			{
				p.tweet.put(line,1);
			}
			
		/* System.out.println(line);
		 *	
		 * System.out.println(line+"\n");
		 */
			
		}
		System.out.println("*****************************************************************************************");
		for(String key : p.tweet.keySet())
		{
			System.out.println(key);
		}
		System.out.println("************************************************************************************");
		System.out.println("TweetCount: "+tweetCount+" Hashcount: "+p.tweet.size());
		br.close();
	}
}
