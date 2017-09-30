package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends BaseHelper {

    private Contacts contactsCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewUserForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getWebSite());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getHomePhone2());
        type(By.name("notes"), contactData.getNotes());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            Select groupSelector = new Select(wd.findElement(By.name("new_group")));
            if (contactData.getGroup() != null) {
                groupSelector.selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitNewUserForm() {click(By.xpath("//div[@id='content']/form/input[21]"));}

    public void initUserCreation() {click(By.linkText("add new"));}

    public void selectUserById(int id) {
        WebElement element = wd.findElement(By.xpath("//tr[@name='entry']//input[@value='"+id+"']"));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void deleteSelectedUser() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitUserDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initUserModification(int id) {
        wd.findElement(By.xpath("//tr[@name='entry']//input[@value='"+id+"']/../../td[8]/a/img")).click();
    }

    public void submitUserModificationForm() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(ContactData contactData) {
        initUserCreation();
        fillNewUserForm(contactData, true);
        submitNewUserForm();
        contactsCache = null;
    }

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> entries = wd.findElements(By.name("entry"));
        for (WebElement entry : entries) {
            List<WebElement> cells = entry.findElements(By.tagName("td"));
            int id = Integer.parseInt(entry.findElement(By.tagName("input")).getAttribute("value"));
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            ContactData user = new ContactData().withFirstName(cells.get(2).getText())
                                                .withLastName(cells.get(1).getText())
                                                .withId(id)
                                                .withAllPhones(allPhones)
                                                .withAllEmails(allEmails)
                                                .withAddress(address);
            contactsCache.add(user);
        }
        return new Contacts(contactsCache);
    }

    public void modify(ContactData contactData) {
        selectUserById(contactData.getId());
        initUserModification(contactData.getId());
        fillNewUserForm(contactData, false);
        submitUserModificationForm();
        contactsCache = null;
    }

    public void delete(ContactData contactData) {
        selectUserById(contactData.getId());
        deleteSelectedUser();
        submitUserDeletion();
        contactsCache = null;
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initUserModification(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String home2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withHomePhone2(home2)
                                .withEmail1(email).withEmail2(email2).withEmail3(email3)
                                .withAddress(address);
    }
}
