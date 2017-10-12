package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        String new_password = String.valueOf(System.currentTimeMillis());
        HttpSession session = app.newSession();
        session.login("administrator","root");
        app.goTo().manageUsersPage();
        Users users = app.db().users();
        UserData user = users.iterator().next();
        String username = user.getUsername();
        String email = user.getEmail();
        app.users().changePassword(email, username, new_password);
        assertTrue(app.newSession().login(username, new_password));
    }

}
