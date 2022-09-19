package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoAddNewContact();
    ContactData contact = new ContactData("Name", "LastName",
            "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru");
    app.getContactHelper().fillContactFormWithCheckGroup(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
