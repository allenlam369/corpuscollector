package newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.response;

import java.util.List;

import newsapilib.src.main.java.com.kwabenaberko.newsapilib.models.Source;

/**
 * Created by Kwabena Berko on 5/7/2018.
 */

public class SourcesResponse {
    private String status;
    private List<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
