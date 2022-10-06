package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddGroupToContactTests extends TestBase {
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

    @Test
    public void testAddGroupToContact() {
        Groups groups = app.db().groups();
        ContactData contact = app.db().contacts().iterator().next();
        Groups groupsOfContact = contact.getGroups();
        int idOfFreeGroup = 0;
        if (groupsOfContact.size() == 0) {
            idOfFreeGroup = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        } else if (groupsOfContact.equals(groups)) {
            app.goTo().groupPage();
            GroupData group = new GroupData().withName("Group").withHeader("Header").withFooter("Footer");
            app.group().create(group);
            idOfFreeGroup = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
        } else {
            app.group().findFreeId(groupsOfContact, groups, idOfFreeGroup);
        }
        app.goTo().home();
        app.contact().addToGroup(contact, idOfFreeGroup);
        ContactData contactAfterAddToGroup = app.db().contacts().iterator().next();
        System.out.println("after size = " + contactAfterAddToGroup.getGroups().size());
        System.out.println("modifiedContact size = " + groupsOfContact.size());
        assertThat(contactAfterAddToGroup.getGroups().size(), equalTo(groupsOfContact.size() + 1));
        //реализовать проверку увеличения групп внутри контакта
        //assertThat(contactAfterAddToGroup.getGroups(), equalTo(groupsOfContact.как-то добавить новую группу)));
        verifyContactListInUI();
    }
}
