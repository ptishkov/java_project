package addressbook.model;

import com.google.gson.annotations.Expose;
import com.google.inject.Exposed;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.checkerframework.checker.signature.qual.CanonicalNameAndBinaryName;

import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String home;
    private String mobile;
    private String work;
    private String allPhones;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;
    private File photo;

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }
    public File getPhoto() { return photo; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getAllEmails() {
        return allEmails;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + home + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals(firstname, that.firstname)) return false;
        if (!Objects.equals(lastname, that.lastname)) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}



