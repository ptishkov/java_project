package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModifications() {
        app.getNavigationHelper().gotoHome();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isContactCreated()) {
            app.getContactHelper().createContact(new ContactData("Name", "LastName",
                    "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru"));
        }
        app.getContactHelper().initContactModification(before - 1);
        app.getContactHelper().fillContactFormWithCheckGroup(new ContactData("Name_new", "LastName_new",
                "Moscow", "+79990001122", "tolokonnikov@yandex.ru"), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
