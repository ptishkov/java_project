package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.annotations.*;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModifications() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Name_new", "LastName_new",
                "Moscow", "+79990001122", "tolokonnikov@yandex.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
    }
}
