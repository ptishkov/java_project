package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isGroupCreated()) {
            app.getGroupHelper().createGroup(new GroupData("1234", "1234", "1234"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().tickFirstGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("3333", "3333", "3333"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();;
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
