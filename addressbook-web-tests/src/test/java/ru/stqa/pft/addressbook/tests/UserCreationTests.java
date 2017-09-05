package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {
        UserData userData = new UserData("First Name", "Middle Name", "Last Name", "Nick Name", "Title", "Company", "Address", "111", "222", "333", "444", "email1@gmail.com", "email2@gmail.com", "email3@gmail.com", "www.google.com", "Address2", "55555", "Notes", "test1");
        app.getNavigationHelper()
           .gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroupWithName(userData.getGroup())) {
            app.getGroupHelper().createGroup(new GroupData(userData.getGroup(), "test2", "test3"));
        }
        app.getNavigationHelper()
           .goHome();
        List<UserData> before = app.getContactHelper().getUsersList();
        app.getContactHelper().createUser(userData);
        app.getNavigationHelper()
           .goHome();
        List<UserData> after = app.getContactHelper().getUsersList();

        Assert.assertEquals(after.size(), before.size()+1);

        userData.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(userData);

        Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
