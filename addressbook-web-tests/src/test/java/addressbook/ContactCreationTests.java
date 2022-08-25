package addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    gotoAddNewContact();
    fillContactForm(new ContactData("Name", "LastName", "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"));
    submitContactCreation();
    gotoHomePage();
  }

}
