package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected final static ApplicationManager app = new ApplicationManager();


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        String status = app.rest().getIssue(issueId).getStatus();
        return !("Resolved".equals(status) || "Closed".equals(status));
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
