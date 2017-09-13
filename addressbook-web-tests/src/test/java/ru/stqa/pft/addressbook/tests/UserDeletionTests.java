package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo()
           .homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new UserData().withFirstName("First Name").withMiddleName("Middle Name").withLastName("Last Name")
                                                .withNickName("Nick Name").withTitle("Title").withCompany("Company").withAddress("Address")
                                                .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withFax("444")
                                                .withEmail1("email1@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                                                .withWebSite("www.google.com").withAddress2("Address2").withHomePhone2("55555").withNotes("Notes").withGroup("test1"));
            app.goTo()
               .homePage();
        }
    }


    @Test
    public void testUserDeletion() {
        Contacts before = app.contacts().all();
        UserData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        app.goTo()
           .homePage();
        assertThat(app.contacts().count(), equalTo(before.size() - 1));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
