package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.goTo().home();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Name").withLastname("LastName")
            .withAddress("Saint-Petersburg").withMobileNumber("+79215553322").withEmail("ptishkov@baltbet.ru");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
