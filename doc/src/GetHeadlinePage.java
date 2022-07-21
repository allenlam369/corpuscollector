package main.java;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.Article;
import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class GetHeadlinePage {
	static String apiKey = "f3bd57b4a6e04d2da8b05763e7e1f2f1";

	public static void main(String[] args) {
		displayPage(1, 0);
	}

	private static void displayPage(int page, int displayedCount) {
		JSONObject json;
		try {
			json = new JSONObject(IOUtils.toString(
					new URL("https://newsapi.org/v2/top-headlines?language=en&pageSize=100&page=" + page + "&apiKey=" + apiKey),
					Charset.forName("UTF-8")));

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			ArticleResponse ar = gson.fromJson(json.toString(), ArticleResponse.class);
			int totalResults = ar.getTotalResults();
			List<Article> aList = ar.getArticles();
			displayedCount += display(aList);

			System.out
					.println("totalResults=" + totalResults + ", page=" + page + ", totalDisplayed=" + displayedCount);

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}

	}

	private static int display(List<Article> aList) {
		for (Article a : aList) {
			System.out.println(a.getDescription());
			System.out.println();
		}
		return aList.size();
	}
}
