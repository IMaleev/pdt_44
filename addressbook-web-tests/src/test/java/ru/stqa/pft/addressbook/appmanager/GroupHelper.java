package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper {

    private Groups groupsCache = null;

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {click(By.linkText("group page"));}

    public void submitGroupCreation() {click(By.name("submit"));}

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {click(By.name("new"));}

    public void deleteSelectedGroups() {click(By.xpath("//div[@id='content']/form/input[5]"));}

    public void selectGroupById(int id) {
        WebElement element = wd.findElement(By.xpath("//span[@class='group']/input[@value='"+id+"']"));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        groupsCache = null;
        returnToGroupPage();
    }

    public Groups all() {
        if (groupsCache != null) {
            return new Groups(groupsCache);
        }
        groupsCache = new Groups();
        List<WebElement> elements = wd.findElements(By.xpath("//span[@class='group']"));
        for (WebElement element: elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupsCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupsCache);
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupsCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupsCache = null;
        returnToGroupPage();
    }

    public int count() {
        return wd.findElements(By.xpath("//span[@class='group']")).size();
    }
}
