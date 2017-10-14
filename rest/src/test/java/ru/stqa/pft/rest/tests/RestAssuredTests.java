package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

    @BeforeClass
    public void init() {
        app.restAssured().auth();
    }

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(10);
        Set<Issue> oldIssues = app.restAssured().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("Test issue description");
        int issueId = app.restAssured().createIssue(newIssue);
        Set<Issue> newIssues = app.restAssured().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

}
