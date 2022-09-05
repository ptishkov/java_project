package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isContactCreated()) {
      app.getContactHelper().createContact(new ContactData("Name", "LastName",
              "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"));
    }
    app.getContactHelper().tickFirstContact();
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().accessContactsDeletion();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}
