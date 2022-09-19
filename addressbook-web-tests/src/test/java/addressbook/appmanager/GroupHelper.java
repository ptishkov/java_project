package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
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

    public void tickFirstGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(new GroupData().withName("1234").withHeader("1234").withFooter("1234"));
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(int index, GroupData group) {
        tickFirstGroup(index);
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(int index) {
        tickFirstGroup(index);
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

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
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
