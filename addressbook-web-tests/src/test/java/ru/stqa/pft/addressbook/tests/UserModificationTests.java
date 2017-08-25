package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        app.getNavigationHelper()
           .goHome();
        if (!app.getContactHelper().isThereAUser()) {
            app.getContactHelper().createUser(new UserData("First Name", "Middle Name", "Last Name", "Nick Name", "Title", "Company", "Address", "111", "222", "333", "444", "email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "www.google.com", "Address2", "55555", "Notes", null));
            app.getNavigationHelper()
               .goHome();
        }
        app.getContactHelper()
           .selectUser();
        app.getContactHelper()
           .initUserModification();
        app.getContactHelper()
           .fillNewUserForm(new UserData("New First Name", "New Middle Name", "New Last Name", "New Nick Name", "New Title", "New Company", "New Address", "1111", "2222", "3333", "4444", "newemail1@gmail.com", "newemail2@gmail.com", "newemail3@gmail.com", "www.newgoogle.com", "New Address2", "5555", "New Notes", null), false);
        app.getContactHelper()
           .submitUserModificationForm();
    }
}
