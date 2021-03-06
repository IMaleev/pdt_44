package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
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
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                     .stream().filter((s) -> !s.equals(""))
                     .collect(Collectors.joining("\n"));
    }
}
