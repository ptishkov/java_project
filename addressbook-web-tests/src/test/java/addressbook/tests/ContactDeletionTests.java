package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
    app.goTo().home();
    app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName").withAddress("Saint-Petersburg")
              .withHomePhone("+79215553322").withMobilePhone("+79111111123").withWorkPhone("+2951717")
              .withEmail("ptishkov@baltbet.ru").withEmail2("ratatatata@gmail.com").withEmail3("vvpitun@mail.ru")
              .withPhoto(new File("src\\test\\resources\\unknown.png")));
    }
  }
  @Test
  public void testContactDeletion() {
    Set<ContactData> before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Set<ContactData> after = app.db().contacts();
    assertThat(after, equalTo(((Contacts) before).without(deletedContact)));
  }

}
