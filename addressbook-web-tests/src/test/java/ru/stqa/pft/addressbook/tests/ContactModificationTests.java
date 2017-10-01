package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

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
    public void testUserModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                                               .withFirstName("New First Name").withMiddleName("New Middle Name").withLastName("New Last Name")
                                               .withNickName("New Nick Name").withTitle("New Title").withCompany("New Company").withAddress("New Address")
                                               .withHomePhone("1111").withMobilePhone("2222").withWorkPhone("3333").withFax("4444")
                                               .withEmail1("newemail1@gmail.com").withEmail2("newemail2@gmail.com").withEmail3("newemail3@gmail.com")
                                               .withWebSite("www.newgoogle.com").withAddress2("New Address2").withHomePhone2("5555").withNotes("New Notes");
        app.goTo().homePage();
        app.contacts().modify(contact);
        app.goTo().homePage();
        assertThat(app.contacts().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactsListInUI();
    }
}
