package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModifications() {
        app.getNavigationHelper().gotoHome();
        if (! app.getContactHelper().isContactCreated()) {
            app.getContactHelper().createContact(new ContactData("Name", "LastName",
                    "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().fillContactFormWithCheckGroup(new ContactData("Name_new", "LastName_new",
                "Moscow", "+79990001122", "tolokonnikov@yandex.ru"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
