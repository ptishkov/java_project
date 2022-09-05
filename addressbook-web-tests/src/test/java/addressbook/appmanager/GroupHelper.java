package addressbook.appmanager;

import addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(new GroupData("1234", "1234", "1234"));
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isGroupCreated() {
        if (isElementPresent(By.name("selected[]"))) {
            return true;
        } else {
            return false;
        }
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
