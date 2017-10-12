package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

//    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String username = String.format("user%s", now);
        String password = "password";
        app.users().registerNewUser(email, username, password);
        assertTrue(app.newSession().login(username, password));
    }

//    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
