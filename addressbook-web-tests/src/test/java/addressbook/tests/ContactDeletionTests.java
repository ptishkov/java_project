package addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().nextAlertIsTrue();
    app.getNavigationHelper().chooseCheckBox();
    app.getContactHelper().submitContactsDeletion();
    app.getContactHelper().accessContactsDeletion();
  }

}
