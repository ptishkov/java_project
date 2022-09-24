package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.goTo().home();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/unknown.png");
    ContactData contact = new ContactData().withFirstname("Name").withLastname("LastName")
            .withAddress("Saint-Petersburg").withHomePhone("+79215553322").withEmail("ptishkov@baltbet.ru").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
