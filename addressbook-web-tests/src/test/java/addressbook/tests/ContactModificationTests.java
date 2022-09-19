package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName")
                    .withAddress("Saint-Petersburg").withMobileNumber("+79215553322").withEmail("ptishkov@baltbet.ru"));
        }
    }
    @Test
    public void testContactModifications() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Name_new").withLastname("LastName_new")
                .withAddress("Moscow").withMobileNumber("+79990001122").withEmail("tolokonnikov@yandex.ru");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
