package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void gotoAddNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void gotoHome() {
        wd.findElement(By.linkText("home")).click();
    }

    public void chooseCheckBox() {
        wd.findElement(By.name("selected[]")).click();
    }
}
