package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
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
    public void testContactModifications() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Name_new").withLastname("LastName_new").withAddress("Moscow")
                .withHomePhone("+79990001122").withMobilePhone("+79993332211").withWorkPhone("+3215555")
                .withEmail("tolokonnikov@yandex.ru").withEmail2("balalaika@rambler.ru").withEmail3("damedvedev@yahoo.com")
                .withPhoto(new File("src\\test\\resources\\25-24.jpg"));
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
