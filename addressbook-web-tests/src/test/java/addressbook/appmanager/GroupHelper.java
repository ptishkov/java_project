package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void tickFirstGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void tickFirstGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(new GroupData().withName("1234").withHeader("1234").withFooter("1234"));
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        tickFirstGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        tickFirstGroupById(group.getId());
        submitGroupsDeletion();
        returnToGroupPage();
    }
    public boolean isGroupCreated() {
        if (isElementPresent(By.name("selected[]"))) {
            return true;
        } else {
            return false;
        }
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withId(id).withName(name);
            groups.add(group);
        }
        return groups;
    }

}
