package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreation() {
        File photo = new File("src/test/resources/photo.jpg");
        UserData userData = new UserData().withFirstName("First Name").withMiddleName("Middle Name").withLastName("Last Name")
                                          .withNickName("Nick Name").withTitle("Title").withCompany("Company").withAddress("Address")
                                          .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withFax("444")
                                          .withEmail1("email1@gmail.com").withEmail2("email2@gmail.com").withEmail3("email3@gmail.com")
                                          .withWebSite("www.google.com").withAddress2("Address2").withHomePhone2("55555").withNotes("Notes").withGroup("test1").withPhoto(photo);
        app.goTo()
           .groupPage();
        if (!app.groups().isThereAGroupWithName(userData.getGroup())) {
            app.groups().create(new GroupData().withName(userData.getGroup()).withHeader("test2").withHeader("test3"));
        }
        app.goTo()
           .homePage();
        Contacts before = app.contacts().all();
        app.contacts().create(userData);
        app.goTo()
           .homePage();
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.withAdded(userData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
