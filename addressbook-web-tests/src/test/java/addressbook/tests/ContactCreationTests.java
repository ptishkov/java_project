package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Name", "LastName",
            "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru", "3333"), true);
  }

}
