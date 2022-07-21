package newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.response;

import java.util.List;

import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.Article;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */

public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
