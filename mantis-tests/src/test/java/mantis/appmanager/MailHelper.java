package mantis.appmanager;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import mantis.model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {
    private final ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser();
    }

    public List<mantis.model.MailMessage> waitForMail(int count, long timeout) throws MessagingException, IOException {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }
    public static mantis.model.MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void start() { wiser.start(); }
    public void stop() { wiser.stop(); }

}
