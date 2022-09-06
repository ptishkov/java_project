package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactFormWithCheckGroup(new ContactData("Name", "LastName",
               "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
