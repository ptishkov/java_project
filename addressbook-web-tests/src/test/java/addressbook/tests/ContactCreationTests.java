package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactFormWithCheckGroup(new ContactData("Name", "LastName",
               "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
