package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {
        app.getNavigationHelper()
           .goHome();
        app.getContactHelper()
           .initUserCreation();
        app.getContactHelper()
           .fillNewUserForm(new UserData("First Name", "Middle Name", "Last Name", "Nick Name", "Title", "Company", "Address", "111", "222", "333", "444", "email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "www.google.com", "Address2", "55555", "Notes"));
        app.getContactHelper()
           .submitNewUserForm();
    }

}
