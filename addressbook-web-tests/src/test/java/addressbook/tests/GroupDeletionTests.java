package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isGroupCreated()) {
      app.getGroupHelper().createGroup(new GroupData("1234", "1234", "1234"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().tickFirstGroup(before.size() - 1);
    app.getGroupHelper().submitGroupsDeletion();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
