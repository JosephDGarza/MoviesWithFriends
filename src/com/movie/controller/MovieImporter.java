package com.movie.controller;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieImporter {

	public static void main(String[] args) {
		
//		Configuration config = new Configuration();
//		config.configure("hibernate.cfg.xml");
//		SessionFactory factory = config.buildSessionFactory();
		
		String[] genreURL = new String[21];
		String[] movieURL = new String[50];
		
		String[] categories = new String[21];	//Categories of movies
		
		String[] movieNames= new String[50]; 	//Hold titles of movies to be passed into MySQL		
		String[] movieImg = new String[50];		//Holds image URL for movies
		//String[] movieTrailer = new String[50];	//Holds trailer for movie
		String[] description = new String[50];	//Holds description of movie
		String[] genres = new String[50];		//Holds LIST of genres of movies
		String[] stars = new String[50];		//Star rating of movie
		String[] rating = new String[50];		//Maturity rating of movie
		String[] length = new String[50];		//Length of the movie
		String[] year = new String[50]; 		//Year movie was made in

		String div = "";
		int h;
		
		try {
			Document doc = Jsoup.connect("http://www.imdb.com/chart/top").get();
			Elements temp = doc.select("li.subnav_item_main");
			int i=0;
			for(Element genre: temp) {
				i++;
				div = genre.getElementsByAttribute("href").toString();
				//This grabs the URL for genres of movies to create list of movies for each. The line below is scraping the URL 
				genreURL[i-1] = "http://www.imdb.com" + (div.substring(div.indexOf("href=\"") +6, div.indexOf("\"> ")));
				div = genre.text(); 
				// Grabbing category name
				categories[i-1] = div;	
			}
			//Category page with list of movies. Grabbing movie's URL's and Names
			for (int j=0; j < 21; j++) {
				System.out.println(j);
				i=0;
				doc = Jsoup.connect(genreURL[j]).get();
				temp = doc.select("h3.lister-item-header");
				for(Element link: temp) {
					i++;
					movieNames[i-1] = link.getElementsByAttribute("href").text();
					div = link.getElementsByAttribute("href").toString();
					movieURL[i-1] = "https://www.imdb.com" + (div.substring(div.indexOf("href=\"") +6, div.indexOf("\">")));
				}
				for(h=0; h < movieURL.length; h++) {
				//Connecting through movie URL to gather img, trailer, description, rating
				doc = Jsoup.connect(movieURL[h]).get();
				
				//Description
				temp = doc.select("div.summary_text");
				div = temp.text();
				description[h] = div;
				
				//Grabbing stars
				temp = doc.select("div.ratingValue");
				div = temp.text();
				stars[h] = div;
				
				//Rating, length, genres, year
				temp = doc.select("div.subtext");
				div = temp.text();
				
				String[] collector;
				collector = div.split("[|()]" );
				
				//Noticed movies such as Vishwaroopam don't all have ratings.
				if(!collector[0].contains("min")){ 
				rating[h] = collector[0];
				length[h] = collector[1];
				genres[h] = collector[2];
				year[h]= collector[3];
				}
				else {
					rating[h] = "No Rating";
					length[h] = collector[0];
					genres[h] = collector[1];
					year[h]= collector[2];
				}
				//movie images gathered here
				temp = doc.select("div.poster");
				div = temp.toString();
				movieImg[h] = "<img src=\"" + div.substring(div.indexOf("src=\"") +5, div.indexOf("\" itemprop")) + "\">";
				System.out.println(movieImg[h]);
				
				}
				
				
				
				
				}
				
			
		
		
		
		
		
		} 
		
		
		
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
