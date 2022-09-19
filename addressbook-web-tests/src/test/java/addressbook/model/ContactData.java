package addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobileNumber;
    private final String email;

    public ContactData(String id, String firstname, String lastname, String address, String mobileNumber, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public ContactData(String firstname, String lastname, String address, String mobileNumber, String email) {
        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public String getId() { return id; }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(firstname, that.firstname)) return false;
        if (!Objects.equals(lastname, that.lastname)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(mobileNumber, that.mobileNumber)) return false;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
