package mantis.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mantis_user_table")
public class UserData extends Users {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")

    private String email;
    public UserData withId(int id) {
        this.id = id;
        return this;
    }
    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }
    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (!Objects.equals(username, userData.username)) return false;
        return Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

}
