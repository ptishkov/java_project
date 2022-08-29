package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isGroupCreated()) {
            app.getGroupHelper().createGroup(new GroupData("1234", "1234", "1234"));
        }
        app.getGroupHelper().tickFirstGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("3333", "3333", "3333"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
