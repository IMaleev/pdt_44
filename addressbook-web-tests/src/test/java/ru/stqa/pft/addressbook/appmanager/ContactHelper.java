package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.UserData;

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
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void submitNewUserForm() {click(By.xpath("//div[@id='content']/form/input[21]"));}

    public void initUserCreation() {click(By.linkText("add new"));}

    public void selectUser() {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click(By.name("selected[]"));
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
}
