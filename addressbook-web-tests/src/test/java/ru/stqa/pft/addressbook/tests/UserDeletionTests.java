package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

    @Test
    public void testUserDeletion() {
        app.getNavigationHelper()
           .goHome();
        app.getContactHelper()
           .selectUser();
        app.getContactHelper()
           .deleteSelectedUser();
        app.getContactHelper()
           .submitUserDeletion();
    }
}
