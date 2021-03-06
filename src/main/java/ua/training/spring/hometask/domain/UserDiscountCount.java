package ua.training.spring.hometask.domain;

import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "user_discount_counts")
public class UserDiscountCount extends DomainObject {

    @Column(name = "name", unique = true)
    @NotNull
    @Size(max = 45)
    private String name;

    @Column(name = "count_tenth_ticket_discount")
    private long countTenthTicketDiscount;

    @Column(name = "count_birthday_discount")
    private long countBirthdayDiscount;

    public UserDiscountCount() {
    }

    public UserDiscountCount(String name, long countTenthTicketDiscount, long countBirthdayDiscount) {
        this.name = name;
        this.countTenthTicketDiscount = countTenthTicketDiscount;
        this.countBirthdayDiscount = countBirthdayDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCountTenthTicketDiscount() {
        return countTenthTicketDiscount;
    }

    public void setCountTenthTicketDiscount(long countTenthTicketDiscount) {
        this.countTenthTicketDiscount = countTenthTicketDiscount;
    }

    public long getCountBirthdayDiscount() {
        return countBirthdayDiscount;
    }

    public void setCountBirthdayDiscount(long countBirthdayDiscount) {
        this.countBirthdayDiscount = countBirthdayDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDiscountCount userDiscountCount = (UserDiscountCount) o;
        return countTenthTicketDiscount == userDiscountCount.countTenthTicketDiscount &&
                countBirthdayDiscount == userDiscountCount.countBirthdayDiscount &&
                Objects.equal(name, userDiscountCount.name) &&
                Objects.equal(getId(), userDiscountCount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, countTenthTicketDiscount, countBirthdayDiscount, getId());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", super.getId())
                .add("name", name)
                .add("countTenthTicketDiscount", countTenthTicketDiscount)
                .add("countBirthdayDiscount", countBirthdayDiscount)
                .toString();
    }

    public static class Builder {

        private UserDiscountCount newDiscountInfo;

        public Builder() {
            newDiscountInfo = new UserDiscountCount();
        }

        public Builder withUserName(String nestedUserName) {
            newDiscountInfo.name = nestedUserName;
            return this;
        }

        public Builder withCountTenthTicketDiscount(long nestedCountTenthTicketDiscount) {
            newDiscountInfo.countTenthTicketDiscount = nestedCountTenthTicketDiscount;
            return this;
        }

        public Builder withCountBirthdayDiscount(long nestedCountBirthdayDiscount) {
            newDiscountInfo.countBirthdayDiscount = nestedCountBirthdayDiscount;
            return this;
        }

        public UserDiscountCount build() {
            return newDiscountInfo;
        }
    }
}
