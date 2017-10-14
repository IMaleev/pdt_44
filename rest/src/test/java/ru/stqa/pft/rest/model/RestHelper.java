package ru.stqa.pft.rest.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

    private ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    private Executor getExecutor() {
        return Executor.newInstance()
                       .auth(app.getProperty("api.token"), "");
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("api.baseUrl")+"/issues.json?limit=300")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public Issue getIssue(int id) throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("api.baseUrl")+"/issues/" + id + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement jsonIssues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issues = new Gson().fromJson(jsonIssues, new TypeToken<Set<Issue>>(){}.getType());
        return issues.iterator().next();
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(app.getProperty("api.baseUrl")+"/issues.json")
                                                   .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                                                             new BasicNameValuePair("description", newIssue.getDescription())))
                                   .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
