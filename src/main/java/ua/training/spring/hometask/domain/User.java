package ua.training.spring.hometask.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "users")
public class User extends DomainObject {

    @Column(name = "first_name")
    @Size(max = 45)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 45)
    private String lastName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "email")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                Objects.equal(password, user.password) &&
                Objects.equal(role, user.role) &&
                Objects.equal(email, user.email) &&
                Objects.equal(dateOfBirth, user.dateOfBirth) &&
                Objects.equal(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, email, dateOfBirth, password, role, getId());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("password", password)
                .add("role", role)
                .add("dateOfBirth", dateOfBirth)
                .toString();
    }
}
