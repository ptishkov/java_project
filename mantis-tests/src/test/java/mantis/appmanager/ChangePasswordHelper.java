package mantis.appmanager;

import mantis.model.UserData;
import mantis.model.Users;
import org.openqa.selenium.By;

import java.sql.*;

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
    public void start(int id) {
        wd.get(app.getProperty("web.baseUrl") + "account_page.php");
        wd.findElement(By.linkText("управление")).click();
        wd.findElement(By.cssSelector("ul.menu > li > a")).click();
        wd.findElement(By.xpath("//tr[" + (id - 1) + "]/td/a")).click();
        wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
    }
    public void finish(String changePasswordLink, String password) {
        wd.get(changePasswordLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        wd.findElement(By.xpath("//input[@value='Изменить учетную запись']")).click();
    }

    public void getUsersData(Users users) {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?" +
                            "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");
            while (rs.next()) {
                users.add(new UserData().withId(rs.getInt("id")).withUsername(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public UserData tickUser(Users users) {
        for (UserData u : users) {
            if (u.getId() != 1) {
                return new UserData().withId(u.getId()).withUsername(u.getUsername()).withEmail(u.getEmail());
            }
        }
        return null;
    }

}
