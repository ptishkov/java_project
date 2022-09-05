package addressbook.appmanager;

import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactFormWithCheckGroup(ContactData contactData, boolean creation) {
        fillContactFormTextFields(contactData);
        if (creation) {
            selectionGroup();
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void gotoAddNewContact() {
        click(By.linkText("add new"));
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }
    public void submitContactCreation() {
        click(By.xpath("//input[21]"));
    }

    public void submitContactsDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void accessContactsDeletion() {
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void initContactModification() {
        click(By.cssSelector("img[alt=\"Edit\"]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void tickFirstContact() {
        click(By.name("selected[]"));
    }

    public boolean isContactCreated() {
        if (isElementPresent(By.name("selected[]"))) {
            return true;
        } else {
            return false;
        }
    }

    public void createContact(ContactData contactData) {
        gotoAddNewContact();
        fillContactFormTextFields(contactData);
        selectionGroup();
        submitContactCreation();
        gotoHomePage();
    }

    public void fillContactFormTextFields(ContactData contactData) {
        type("firstname", contactData.getFirstname());
        type("lastname", contactData.getLastname());
        type("address", contactData.getAddress());
        type("mobile", contactData.getMobileNumber());
        type("email", contactData.getEmail());
    }

    public void selectionGroup() {
        try {
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        } catch(NoSuchElementException e) {
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(0);
        }
    }

    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }
}
