package addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("1234", "1234", "1234"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
