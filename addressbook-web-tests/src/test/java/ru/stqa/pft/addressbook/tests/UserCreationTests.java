package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

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
        app.getContactHelper().createUser(userData);
    }

}
