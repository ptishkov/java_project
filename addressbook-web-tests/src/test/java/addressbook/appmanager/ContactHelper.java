package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }
    public void gotoAddNewContact() {
        click(By.linkText("add new"));
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

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href=\"edit.php?id=" + id + "\"")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void tickFirstContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
    }

    public void create(ContactData contactData) {
        gotoAddNewContact();
        fillContactFormTextFields(contactData);
        submitContactCreation();
        contactCashe = null;
        gotoHomePage();
    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactFormTextFields(contact);
        submitContactModification();
        contactCashe = null;
        returntoHome();
    }

    public void delete(ContactData contact) {
        tickFirstContactById(contact.getId());
        submitContactsDeletion();
        accessContactsDeletion();
        contactCashe = null;
        returntoHome();
    }
    private void returntoHome() {
        click(By.linkText("home"));
    }

    public void fillContactFormTextFields(ContactData contactData) {
        type("firstname", contactData.getFirstname());
        type("lastname", contactData.getLastname());
        type("address", contactData.getAddress());
        type("mobile", contactData.getHome());
        type("email", contactData.getEmail());
        attach("photo", contactData.getPhoto());
    }

    private Contacts contactCashe = null;
    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        int tr = 2;
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + tr + "]/td[2]")).getText();
            String firstname = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + tr + "]/td[3]")).getText();
            String address = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + tr + "]/td[4]")).getText();
            String allEmails = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + tr + "]/td[5]")).getText().trim();
            String allPhones = element.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + tr + "]/td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails);
            contactCashe.add(contact);
            tr++;
        }
        return new Contacts(contactCashe);
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement((By.name("firstname"))).getAttribute("value");
        String lastname = wd.findElement((By.name("lastname"))).getAttribute("value");
        String address = wd.findElement((By.name("address"))).getText();
        String home = wd.findElement((By.name("home"))).getAttribute("value");
        String mobile = wd.findElement((By.name("mobile"))).getAttribute("value");
        String work = wd.findElement((By.name("work"))).getAttribute("value");
        String email = wd.findElement((By.name("email"))).getAttribute("value");
        String email2 = wd.findElement((By.name("email2"))).getAttribute("value");
        String email3 = wd.findElement((By.name("email3"))).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
