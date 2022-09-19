package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoHome();
        if (! app.getContactHelper().isContactCreated()) {
            app.getContactHelper().createContact(new ContactData("Name", "LastName",
                    "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"));
        }
    }
    @Test
    public void testContactModifications() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Name_new", "LastName_new",
                "Moscow", "+79990001122", "tolokonnikov@yandex.ru");
        app.getContactHelper().modifyContact(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
