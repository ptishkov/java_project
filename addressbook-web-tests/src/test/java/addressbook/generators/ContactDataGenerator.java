package addressbook.generators;
import addressbook.model.ContactData;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "ContactCount")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }


    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                    contact.getHome(), contact.getMobile(), contact.getWork(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getPhoto()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        File photo = new File("src/test/resources/unknown.png");
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("firstname %s", i))
                    .withLastname(String.format("lastname %s", i)).withAddress(String.format("address %s", i))
                    .withHomePhone(String.format("111 %s", i)).withMobilePhone(String.format("2222 %s", i)).withWorkPhone(String.format("33333 %s", i))
                    .withEmail(String.format("email@mail.ru %s", i)).withEmail2(String.format("email2@mail.ru %s", i))
                    .withEmail3(String.format("email3@mail.ru %s", i)).withPhoto(photo));
        }
        return contacts;
    }
}
