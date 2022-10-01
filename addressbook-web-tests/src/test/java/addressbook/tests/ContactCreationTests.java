package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
              .withHomePhone(split[3]).withMobilePhone(split[4]).withWorkPhone(split[5])
              .withEmail(split[6]).withEmail2(split[7]).withEmail3(split[8]).withPhoto(new File(split[9]))});
      line = reader.readLine();
    }
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
