package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

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
        List<UserData> before = app.getContactHelper().getUsersList();
        app.getContactHelper()
           .selectUser(before.size()-1);
        app.getContactHelper()
           .initUserModification();
        UserData newUserData = new UserData("New First Name", "New Middle Name", "New Last Name", "New Nick Name", "New Title", "New Company", "New Address", "1111", "2222", "3333", "4444", "newemail1@gmail.com", "newemail2@gmail.com", "newemail3@gmail.com", "www.newgoogle.com", "New Address2", "5555", "New Notes", null, before.get(before.size()-1).getId());
        app.getContactHelper()
           .fillNewUserForm(newUserData, false);
        app.getContactHelper()
           .submitUserModificationForm();
        app.getNavigationHelper()
           .goHome();

        List<UserData> after = app.getContactHelper().getUsersList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(newUserData);

        Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
