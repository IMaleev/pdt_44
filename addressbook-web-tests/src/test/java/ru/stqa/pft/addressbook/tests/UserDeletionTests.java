package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

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
        List<UserData> before = app.getContactHelper().getUsersList();
        app.getContactHelper()
           .selectUser(before.size()-1);
        app.getContactHelper()
           .deleteSelectedUser();
        app.getContactHelper()
           .submitUserDeletion();
        app.getNavigationHelper()
           .goHome();
        List<UserData> after = app.getContactHelper().getUsersList();

        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
