package ua.training.spring.hometask.domain;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "users")
public class User extends DomainObject {

    @Column(name = "first_name")
    @Size(max = 45)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 45)
    private String lastName;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Ticket.class, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new TreeSet<>();

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equal(firstName, user.firstName) &&
                Objects.equal(lastName, user.lastName) &&
                Objects.equal(email, user.email) &&
                Objects.equal(dateOfBirth, user.dateOfBirth) &&
                Objects.equal(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, email, dateOfBirth, getId());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("dateOfBirth", dateOfBirth)
                .toString();
    }
}
