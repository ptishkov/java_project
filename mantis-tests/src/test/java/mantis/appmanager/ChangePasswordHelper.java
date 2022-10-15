package mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase{

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        wd.findElement(By.xpath("//input[@value='Войти']")).click();
    }
    public void start() {
        wd.get(app.getProperty("web.baseUrl") + "account_page.php");
        wd.findElement(By.linkText("управление")).click();
        wd.get(app.getProperty("web.baseUrl") + "manage_overview_page.php");
        wd.findElement(By.cssSelector("ul.menu > li > a")).click();
        wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
        wd.findElement(By.xpath("//tr[2]/td/a")).click();
        wd.get(app.getProperty("web.baseUrl") + "manage_user_edit_page.php?user_id=3");
        //wd.get(app.getProperty("web.baseUrl") + "manage_user_edit_page.php?user_id=" + userIdFromDB);
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }
    public void finish(String changePasswordLink, String password) {
        wd.get(changePasswordLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        wd.findElement(By.xpath("//input[@value='Изменить учетную запись']")).click();
        //Чтобы можно было руками проверить логин, вывод пароля в консоль
        System.out.println("Пароль пользователя успешно изменён! Новый пароль: " + password);
    }

}
