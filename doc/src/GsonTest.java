package main.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class GsonTest {

	public static void main(String[] args) throws IOException {
		String fn = "C:\\Users\\SITO(WP)71\\Google Drive\\Eclipse\\ee\\testNewsApi\\doc\\resp2.json";
		Reader reader = new FileReader(fn);

		Gson gson = new Gson();

		ArticleResponse ar = gson.fromJson(reader, ArticleResponse.class);
		
		System.out.println(ar.getArticles().size());

		System.out.println("total results: " + ar.getTotalResults());
		
		reader.close();
	}

}
