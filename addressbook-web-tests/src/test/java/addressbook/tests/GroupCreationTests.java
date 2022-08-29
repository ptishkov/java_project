package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("1234", "1234", "1234"));
  }

}
