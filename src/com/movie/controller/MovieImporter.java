package com.movie.controller;
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
		
		
		String[] movieNames= new String[50]; 	//Hold titles of movies to be passed into MySQL		
		String[] movieImg = new String[50];		//Holds image URL for movies
		String[] movieTrailer = new String[50];	//Holds trailer for movie
		String[] description = new String[50];	//Holds description of movie
		String[] genres = new String[50];		//Holds genres of movies
		
		int[] year = new int[50]; 				//Year movie was made in
		double[] rating = new double[50];		//Star rating of movie
		int[] length = new int[50];				//Length of the movie
		
		
		
		
		
	}
}
