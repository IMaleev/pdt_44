package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() {
        app.getNavigationHelper()
           .goHome();
        app.getContactHelper()
           .selectUser();
        app.getContactHelper()
           .initUserModification();
        app.getContactHelper()
           .fillNewUserForm(new UserData("New First Name", "New Middle Name", "New Last Name", "New Nick Name", "New Title", "New Company", "New Address", "1111", "2222", "3333", "4444", "newemail1@gmail.com", "newemail2@gmail.com", "newemail3@gmail.com", "www.newgoogle.com", "New Address2", "5555", "New Notes"));
        app.getContactHelper()
           .submitUserModificationForm();
    }
}
