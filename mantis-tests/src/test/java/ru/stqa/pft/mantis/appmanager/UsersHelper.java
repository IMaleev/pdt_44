package ru.stqa.pft.mantis.appmanager;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class UsersHelper extends HelperBase {

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

    public void registerNewUser(String email, String username, String password) throws MessagingException {
        app.james().createUser(username, password);
        app.mantisUI().startRegistration(username, email);
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.mantisUI().finishRegistration(confirmationLink, password);
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public void changePassword(String email, String username, String new_password)
            throws MessagingException, IOException {
        app.mantisUI().startPasswordChange(email);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
        String confirmationLink = app.users().findConfirmationLink(mailMessages, email);
        app.mantisUI().finishPasswordChange(confirmationLink, new_password);
    }

}
