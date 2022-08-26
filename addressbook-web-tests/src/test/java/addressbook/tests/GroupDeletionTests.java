package addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().tickFirstGroup();
    app.getGroupHelper().submitGroupsDeletion();
    app.getNavigationHelper().gotoGroupPage();
  }

}
