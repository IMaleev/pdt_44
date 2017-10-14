package ru.stqa.pft.rest.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.util.Set;

public class RestAssuredHelper {

    private ApplicationManager app;

    public RestAssuredHelper(ApplicationManager app) {
        this.app = app;
    }

    public void auth() {
        RestAssured.authentication = RestAssured.basic(app.getProperty("api.token"), "");
    }

    public Issue getIssue(int id) {
//        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(id));
//        return new Issue().withId(issueData.getId().intValue())
//                          .withSummary(issueData.getSummary())
//                          .withDescription(issueData.getDescription())
//                          .withProject(new Project().withId(issueData.getProject().getId().intValue())
//                                                    .withName(issueData.getProject().getName()))
//                          .withStatus(issueData.getStatus().getName());
        return new Issue();
    }

    public Set<Issue> getIssues() throws IOException {
        String json = RestAssured.get(app.getProperty("api.baseUrl")+"/issues.json?limit=300").asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                                 .parameter("subject", newIssue.getSubject())
                                 .parameter("description", newIssue.getDescription())
                                 .post(app.getProperty("api.baseUrl")+"/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
