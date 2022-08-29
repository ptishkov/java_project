package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModifications() {
        app.getNavigationHelper().gotoHome();
        if (! app.getContactHelper().isContactCreated()) {
            app.getContactHelper().createContact(new ContactData("Name", "LastName",
                    "Saint-Petersburg", "+79215553322", "ptishkov@baltbet.ru", "3333"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Name_new", "LastName_new",
                "Moscow", "+79990001122", "tolokonnikov@yandex.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
    }
}
