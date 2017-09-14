package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo()
           .homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new UserData().withFirstName("First Name").withMiddleName("Middle Name").withLastName("Last Name")
                                                .withNickName("Nick Name").withTitle("Title").withCompany("Company").withAddress("Address")
                                                .withHomePhone("+7 (111)").withMobilePhone("22 22").withWorkPhone("333-333").withFax("444")
                                                .withEmail1("email1@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                                                .withWebSite("www.google.com").withAddress2("Address2").withHomePhone2("55555").withNotes("Notes").withGroup("test1"));
            app.goTo()
               .homePage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        UserData contact = app.contacts().all().iterator().next();
        UserData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(UserData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone2())
                     .stream().filter((s) -> !s.equals(""))
                     .map(ContactPhoneTests::cleaned)
                     .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
