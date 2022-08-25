package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("1234", "1234", "1234"));
    app.submitGroupCreation();
    app.gotoGroupPage();
  }

}
