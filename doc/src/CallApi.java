package main.java;

import java.util.List;

import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.Article;
import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.response.ArticleResponse;

public class CallApi {

	static String apiKey = "f3bd57b4a6e04d2da8b05763e7e1f2f1";

	public static void main(String[] args) {
		NewsApiClient client = new NewsApiClient(apiKey);

//		client.getTopHeadlines(new TopHeadlinesRequest.Builder().q("bitcoin").language("en").build(),

		client.getTopHeadlines(new TopHeadlinesRequest.Builder().language("en").page(2).build(),

				new NewsApiClient.ArticlesResponseCallback() {
					@Override
					public void onSuccess(ArticleResponse response) {

						List<Article> aList = response.getArticles();
						if (aList != null) {
							for (Article a : aList) {
								String desc = a.getDescription();
								System.out.println(desc);
								System.out.println();
							}
						}

						System.out.println("article count: " + aList.size());

//						System.out.println(response.getArticles().get(0).getTitle());
					}

					@Override
					public void onFailure(Throwable throwable) {
						System.out.println(throwable.getMessage());
					}
				});

	}
}
