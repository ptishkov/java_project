package mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import mantis.model.MailMessage;
import mantis.model.UserData;
import mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests  extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        //Начальная инициализация переменных, нужен по факту только пароль
        long now = System.currentTimeMillis();
        String newPassword = String.format("password%s", now);
        Users users = new Users();

        //Нужно достать юзеров из БД bugtracker, таблицы mantis_user_table и выбрать того, кто не администратор (не id=1)
        app.changePassword().getUsersData(users);
        UserData user = app.changePassword().tickUser(users);

        //1. Администратор заходит в мантис
        app.changePassword().login();
        //2. Выбор пользователя + нажатие кнопки "Сбросить пароль"
        app.changePassword().start(user.getUsername());
        //3. Отлавливаем письмо на почту
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String changePasswordLink = findChangePasswordLink(mailMessages, user.getEmail());
        //4. Меняем пароль пользователя
        app.changePassword().finish(changePasswordLink, newPassword);

        //5. Конечная проверка: логин под изменённым паролем
        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
    private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
