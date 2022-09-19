package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreation(){
    app.goTo().GroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("1234").withHeader("1234").withFooter("1234");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
