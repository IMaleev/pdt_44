package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

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

    public void selectUser(int index) {
        WebElement element = wd.findElements(By.name("entry")).get(index).findElement(By.name("selected[]"));
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

    public void initUserModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitUserModificationForm() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createUser(UserData userData) {
        initUserCreation();
        fillNewUserForm(userData, true);
        submitNewUserForm();
    }

    public List<UserData> getUsersList() {
        List<UserData> users = new ArrayList<>();
        List<WebElement> entries = wd.findElements(By.name("entry"));
        for (WebElement entry : entries) {
            List<WebElement> cells = entry.findElements(By.tagName("td"));
            int id = Integer.parseInt(entry.findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData(cells.get(2).getText(), null, cells.get(1).getText(), null, null, null, null,
                                         null, null, null, null, null, null, null, null, null, null, null, null, id);
            users.add(user);
        }
        return users;
    }
}
