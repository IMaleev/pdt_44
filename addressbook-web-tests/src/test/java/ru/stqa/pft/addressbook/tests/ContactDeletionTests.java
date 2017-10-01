package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contacts().create(new ContactData().withFirstName("First Name").withMiddleName("Middle Name").withLastName("Last Name")
                                                   .withNickName("Nick Name").withTitle("Title").withCompany("Company").withAddress("Address")
                                                   .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withFax("444")
                                                   .withEmail1("email1@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                                                   .withWebSite("www.google.com").withAddress2("Address2").withHomePhone2("55555").withNotes("Notes"));
        }
    }


    @Test
    public void testUserDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contacts().delete(deletedContact);
        app.goTo().homePage();
        assertThat(app.contacts().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
        verifyContactsListInUI();
    }
}
