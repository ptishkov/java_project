package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Set;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName")
                    .withAddress("Saint-Petersburg").withMobileNumber("+79215553322").withEmail("ptishkov@baltbet.ru"));
        }
    }
    @Test
    public void testContactModifications() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Name_new").withLastname("LastName_new")
                .withAddress("Moscow").withMobileNumber("+79990001122").withEmail("tolokonnikov@yandex.ru");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
