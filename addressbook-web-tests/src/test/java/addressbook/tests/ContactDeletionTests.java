package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoHome();
    if (! app.getContactHelper().isContactCreated()) {
      app.getContactHelper().createContact(new ContactData("Name", "LastName",
              "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru", "3333"), true);
    }
    app.getContactHelper().tickFirstContact();
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().accessContactsDeletion();
    app.getNavigationHelper().gotoHome();
  }

}
