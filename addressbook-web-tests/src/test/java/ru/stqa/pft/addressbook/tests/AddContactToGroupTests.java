package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.groups().create(new GroupData().withName("test1").withHeader("test2").withHeader("test3"));
        }
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
    public void testAddContactToGroup() {
        ContactData contact = getContactNotInGroup();
        GroupData group = getGroupForContact(contact);

        app.goTo().homePage();
        Groups before = contact.getGroups();
        app.contacts().addContactToGroup(contact, group);
        app.goTo().homePage();
        Groups after = app.db().getContactById(contact.getId()).getGroups();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(group)));
        verifyContactsListInUI();
    }

    private GroupData getGroupForContact(ContactData contact) {
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if (!contact.getGroups().contains(group)) {
                return group;
            }
        }
        app.goTo().groupPage();
        app.groups().create(new GroupData().withName("test1").withHeader("test2").withHeader("test3"));
        int id = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        return app.db().getGroupById(id);
    }

    private ContactData getContactNotInGroup() {
        Groups groups = app.db().groups();
        for (ContactData contact : app.db().contacts()) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        app.goTo().homePage();
        app.contacts().create(new ContactData().withFirstName("First Name").withMiddleName("Middle Name").withLastName("Last Name")
                                               .withNickName("Nick Name").withTitle("Title").withCompany("Company").withAddress("Address")
                                               .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withFax("444")
                                               .withEmail1("email1@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                                               .withWebSite("www.google.com").withAddress2("Address2").withHomePhone2("55555").withNotes("Notes"));
        int id = app.db().contacts().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        return app.db().getContactById(id);
    }
}
