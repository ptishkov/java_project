package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isGroupCreated()) {
      app.getGroupHelper().createGroup(new GroupData("1234", "1234", "1234"));
    }
    app.getGroupHelper().tickFirstGroup();
    app.getGroupHelper().submitGroupsDeletion();
    app.getGroupHelper().returnToGroupPage();
  }

}
