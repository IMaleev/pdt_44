package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(";");
                list.add(new Object[] { new ContactData().withFirstName(split[0])
                                                         .withMiddleName(split[1])
                                                         .withLastName(split[2])
                                                         .withNickName(split[3])
                                                         .withTitle(split[4])
                                                         .withCompany(split[5])
                                                         .withAddress(split[6])
                                                         .withHomePhone(split[7])
                                                         .withMobilePhone(split[8])
                                                         .withWorkPhone(split[9])
                                                         .withFax(split[10])
                                                         .withEmail1(split[11])
                                                         .withEmail2(split[12])
                                                         .withEmail3(split[13])
                                                         .withWebSite(split[14])
                                                         .withAddress2(split[15])
                                                         .withHomePhone2(split[16])
                                                         .withNotes(split[17]).withGroup(split[18]) });
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            while (reader.ready()) {
                String line = reader.readLine();
                xml += line;
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream()
                           .map((g) -> new Object[] { g })
                           .collect(Collectors.toList())
                           .iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            while (reader.ready()) {
                String line = reader.readLine();
                json += line;
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
            return contacts.stream()
                           .map((g) -> new Object[] { g })
                           .collect(Collectors.toList())
                           .iterator();
        }
    }

    @Test(dataProvider = "validContactsJson")
    public void testUserCreation(ContactData contact) {
        File photo = new File("src/test/resources/photo.jpg");
        app.goTo()
           .groupPage();
        if (!app.groups().isThereAGroupWithName(contact.getGroup())) {
            app.groups().create(new GroupData().withName(contact.getGroup()).withHeader("test2").withHeader("test3"));
        }
        app.goTo()
           .homePage();
        Contacts before = app.contacts().all();
        app.contacts().create(contact.withPhoto(photo));
        app.goTo()
           .homePage();
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
