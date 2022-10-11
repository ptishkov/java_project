package addressbook.appmanager;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type("group_name", groupData.getName());
        type("group_header", groupData.getHeader());
        type("group_footer", groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void submitGroupsDeletion() {
        click(By.xpath("//input[5]"));
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void initGroupModification() { click(By.name("edit")); }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void tickFirstGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCashe = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        tickFirstGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCashe = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        tickFirstGroupById(group.getId());
        submitGroupsDeletion();
        groupCashe = null;
        returnToGroupPage();
    }
    public int findFreeId(Groups groupsOfContact, Groups groupsFromDb) {
        int freeGroup = 0;
        for (GroupData group : groupsFromDb) {
            if (!groupsOfContact.contains(group)) {
                freeGroup = group.getId();
            }
        }
        return freeGroup;
    }
    public GroupData findGroup(Groups groups, int idGroup) {
        GroupData selectedGroup = new GroupData();
        for (GroupData group : groups) {
            if (group.getId() == idGroup) {
                selectedGroup = group;
            }
        }
        return selectedGroup;
    }
    public boolean isGroupCreated() {
        return isElementPresent(By.name("selected[]"));
    }
    private Groups groupCashe = null;
    public Groups all() {
        if (groupCashe != null) {
            return new Groups(groupCashe);
        }
        groupCashe = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCashe.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCashe);
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

}
