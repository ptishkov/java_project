package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
    @BeforeMethod
    public void groupsIsCreated() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            Groups before = app.db().groups();
            GroupData group = new GroupData().withName("testGroup").withHeader("testHeader").withFooter("testFooter");
            app.group().create(group);
            assertThat(app.group().count(), equalTo(before.size() + 1));
            Groups after = app.db().groups();
            assertThat(after, equalTo(
                    before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }
    }
    @BeforeMethod
    public void contactIsCreated() {
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            Contacts before = app.db().contacts();
            ContactData contact = new ContactData().withFirstname("testFirstname").withLastname("testLastname").withAddress("testAddress")
                            .withHomePhone("+77777").withMobilePhone("+88888").withWorkPhone("+99999")
                            .withEmail("testEmail").withEmail2("testEmail2").withEmail3("testEmail3")
                            .withPhoto(new File("src\\test\\resources\\unknown.png"));
            app.contact().create(contact);
            assertThat(app.contact().count(), equalTo(before.size() + 1));
            Contacts after = app.db().contacts();
            assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        ContactData contact = app.db().contacts().iterator().next();
        Groups groupsOfContact = contact.getGroups();
        int idOfFreeGroup = 0;
        if (groupsOfContact.size() == 0) {
            idOfFreeGroup = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        } else if (groupsOfContact.equals(groups)) {
            Groups before = app.db().groups();
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("Group").withHeader("Header").withFooter("Footer");
            app.group().create(group);
            assertThat(app.group().count(), equalTo(before.size() + 1));
            Groups after = app.db().groups();
            assertThat(after, equalTo(
                    before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
            idOfFreeGroup = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        } else {
            idOfFreeGroup = app.group().findFreeId(groupsOfContact, groups, idOfFreeGroup);
        }
        app.goTo().home();
        app.contact().addToGroup(contact, idOfFreeGroup);
        ContactData contactAfterAddToGroup = app.db().contacts().iterator().next();
        assertThat(contactAfterAddToGroup.getGroups().size(), equalTo(groupsOfContact.size() + 1));
        GroupData addedGroup = app.group().findGroup(app.db().groups(), idOfFreeGroup);
        assertThat(contactAfterAddToGroup.getGroups(), equalTo(groupsOfContact.withAdded(addedGroup)));
        verifyContactListInUI();
        app.goTo().groupPage();
        verifyGroupListInUI();
    }
}
