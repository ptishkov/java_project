package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/unknown.png");
    list.add(new Object[] {new ContactData().withFirstname("firstname 1").withLastname("lastname 1").withAddress("sity 1")
            .withHomePhone("+111").withMobilePhone("+222").withWorkPhone("+333")
            .withEmail("email_1@mail.ru").withEmail2("email2_1@mail.ru").withEmail3("email3_1@mail.ru")
            .withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("firstname 2").withLastname("lastname 2").withAddress("sity 2")
            .withHomePhone("++111").withMobilePhone("++222").withWorkPhone("++333")
            .withEmail("email_2@mail.ru").withEmail2("email2_2@mail.ru").withEmail3("email3_2@mail.ru")
            .withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("firstname 3").withLastname("lastname 3").withAddress("sity 3")
            .withHomePhone("+++111").withMobilePhone("+++222").withWorkPhone("+++333")
            .withEmail("email_3@mail.ru").withEmail2("email2_3@mail.ru").withEmail3("email3_3@mail.ru")
            .withPhoto(photo)});
    return list.iterator();
  }
  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().home();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
