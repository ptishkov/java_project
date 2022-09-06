package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isContactCreated()) {
      app.getContactHelper().createContact(new ContactData("Name", "LastName",
              "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().tickFirstContact(before.size() - 1);
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().accessContactsDeletion();
    app.getNavigationHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

}
