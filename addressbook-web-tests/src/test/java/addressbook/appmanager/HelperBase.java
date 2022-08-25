package addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver wd;
    private boolean acceptNextAlert = true;
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(String locator, String text) {
        click(By.name(locator));
        wd.findElement(By.name(locator)).clear();
        wd.findElement(By.name(locator)).sendKeys(text);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public void nextAlertIsTrue() {
        acceptNextAlert = true;
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = ((Alert) alert).getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            nextAlertIsTrue();
        }
    }
}
