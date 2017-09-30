package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contacts count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try {
            jcommander.parse(args);
        } catch (ParameterException e) {
            jcommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = GenerateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unsupported format "+ format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact: contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                                           contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                                           contact.getNickName(), contact.getTitle(), contact.getCompany(), contact.getAddress(),
                                           contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getFax(),
                                           contact.getEmail1(), contact.getEmail2(), contact.getEmail3(),
                                           contact.getWebSite(), contact.getAddress2(), contact.getHomePhone2(), contact.getNotes(),
                                           contact.getGroup()));
            }
        }
    }

    private List<ContactData> GenerateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i<count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("First Name %s", i))
                                          .withMiddleName(String.format("Middle Name %s", i))
                                          .withLastName(String.format("Last Name %s", i))
                                          .withNickName(String.format("Nick Name %s", i))
                                          .withTitle(String.format("Title %s", i))
                                          .withCompany(String.format("Company %s", i))
                                          .withAddress(String.format("Address %s", i))
                                          .withHomePhone(String.format("%s", i))
                                          .withMobilePhone(String.format("%s", i))
                                          .withWorkPhone(String.format("%s", i))
                                          .withFax(String.format("%s", i))
                                          .withEmail1(String.format("email1%s@gmail.com", i))
                                          .withEmail2(String.format("email2%s@gmail.com", i))
                                          .withEmail3(String.format("email3%s@gmail.com", i))
                                          .withWebSite(String.format("www.site%s.com", i))
                                          .withAddress2(String.format("Address2 %s", i))
                                          .withHomePhone2(String.format("2%s", i))
                                          .withNotes(String.format("Notes %s", i))
                                          .withGroup(String.format("Group %s", i)));
        }
        return contacts;
    }
}
