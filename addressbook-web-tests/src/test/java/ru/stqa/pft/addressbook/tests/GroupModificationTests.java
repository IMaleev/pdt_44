package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo()
           .groupPage();
        if (app.groups().all().size() == 0) {
            app.groups().create(new GroupData().withName("test1").withHeader("test2").withHeader("test3"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.groups().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test4").withHeader("test5").withHeader("test6");
        app.groups().modify(group);
        assertThat(app.groups().count(), equalTo(before.size()));
        Groups after = app.groups().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }
}
