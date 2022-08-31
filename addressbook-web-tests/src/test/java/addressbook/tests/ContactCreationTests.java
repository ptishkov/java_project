package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillContactFormWithCheckGroup(new ContactData("Name", "LastName",
            "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru", "3333"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
  }

}
