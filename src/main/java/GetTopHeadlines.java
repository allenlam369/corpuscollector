package main.java;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.Article;
import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.response.ArticleResponse;

//  Developer accounts are limited to a max of 100 results
public class GetTopHeadlines {
	static String apiKey = "f3bd57b4a6e04d2da8b05763e7e1f2f1";
	static String[] categories = { "business", "general", "health", "science", "technology" };
	static Logger log = LoggerFactory.getLogger(GetTopHeadlines.class);

	public static void main(String[] args) throws JSONException, MalformedURLException, IOException {
		displayPage(1, categories[3]);

	}

	private static void displayPage(int page, String cat) {
		JSONObject json;
		try {
			json = new JSONObject(
					IOUtils.toString(
							new URL("https://newsapi.org/v2/top-headlines?category=" + cat
									+ "&language=en&pageSize=100&page=" + page + "&apiKey=" + apiKey),
							Charset.forName("UTF-8")));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			ArticleResponse ar = gson.fromJson(json.toString(), ArticleResponse.class);
//			int totalResult = ar.getTotalResults();
			List<Article> aList = ar.getArticles();
			int displayedCount = display(aList);

			System.out.printf("total articles = %d in %s\n", displayedCount, cat);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

	}

	private static int display(List<Article> aList) {
		for (Article a : aList) {
			String desc = a.getDescription();

			if (desc == null) {
				desc = a.getTitle();
			}
			
			DB.save(desc);
			
			System.out.println(desc);
			System.out.println();
		}
		return aList.size();
	}

}
