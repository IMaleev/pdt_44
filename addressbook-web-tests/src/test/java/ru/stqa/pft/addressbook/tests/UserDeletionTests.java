package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

    @Test
    public void testUserDeletion() {
        app.getNavigationHelper()
           .goHome();
        if (!app.getContactHelper().isThereAUser()) {
            app.getContactHelper().createUser(new UserData("First Name", "Middle Name", "Last Name", "Nick Name", "Title", "Company", "Address", "111", "222", "333", "444", "email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "www.google.com", "Address2", "55555", "Notes", "test1"));
            app.getNavigationHelper()
               .goHome();
        }
        app.getContactHelper()
           .selectUser();
        app.getContactHelper()
           .deleteSelectedUser();
        app.getContactHelper()
           .submitUserDeletion();
    }
}
