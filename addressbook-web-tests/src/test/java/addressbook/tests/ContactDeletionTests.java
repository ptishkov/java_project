package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().home();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName")
              .withAddress("Saint-Petersburg").withMobileNumber("+79215553322").withEmail("ptishkov@baltbet.ru"));
    }
  }
  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
