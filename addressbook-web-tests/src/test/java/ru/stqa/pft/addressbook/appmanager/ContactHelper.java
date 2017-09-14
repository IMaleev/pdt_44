package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class ContactHelper extends BaseHelper {

    private Contacts contactsCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillNewUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstName());
        type(By.name("middlename"), userData.getMiddleName());
        type(By.name("lastname"), userData.getLastName());
        type(By.name("nickname"), userData.getNickName());
        type(By.name("title"), userData.getTitle());
        type(By.name("company"), userData.getCompany());
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getHomePhone());
        type(By.name("mobile"), userData.getMobilePhone());
        type(By.name("work"), userData.getWorkPhone());
        type(By.name("fax"), userData.getFax());
        type(By.name("email"), userData.getEmail1());
        type(By.name("email2"), userData.getEmail2());
        type(By.name("email3"), userData.getEmail3());
        type(By.name("homepage"), userData.getWebSite());
        type(By.name("address2"), userData.getAddress2());
        type(By.name("phone2"), userData.getHomePhone2());
        type(By.name("notes"), userData.getNotes());

        if (creation) {
            Select groupSelector = new Select(wd.findElement(By.name("new_group")));
            if (userData.getGroup() != null) {
                groupSelector.selectByVisibleText(userData.getGroup());
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

    public void create(UserData userData) {
        initUserCreation();
        fillNewUserForm(userData, true);
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
            UserData user = new UserData().withFirstName(cells.get(2).getText())
                                          .withLastName(cells.get(1).getText())
                                          .withId(id)
                                          .withAllPhones(allPhones)
                                          .withAllEmails(allEmails)
                                          .withAddress(address);
            contactsCache.add(user);
        }
        return new Contacts(contactsCache);
    }

    public void modify(UserData userData) {
        selectUserById(userData.getId());
        initUserModification(userData.getId());
        fillNewUserForm(userData, false);
        submitUserModificationForm();
        contactsCache = null;
    }

    public void delete(UserData userData) {
        selectUserById(userData.getId());
        deleteSelectedUser();
        submitUserDeletion();
        contactsCache = null;
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name='entry']")).size();
    }

    public UserData infoFromEditForm(UserData contact) {
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
        return new UserData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                             .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withHomePhone2(home2)
                             .withEmail1(email).withEmail2(email2).withEmail3(email3)
                             .withAddress(address);
    }
}
