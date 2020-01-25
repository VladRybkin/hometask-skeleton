package ua.training.spring.hometask.domain;

import com.google.common.base.Objects;

public class DiscountCountInfo extends DomainObject {

    private String name;

    private long countTenthTicketDiscount;

    private long countBirthdayDiscount;

    public DiscountCountInfo() {
    }

    public DiscountCountInfo(String name, long countTenthTicketDiscount, long countBirthdayDiscount) {
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
        DiscountCountInfo that = (DiscountCountInfo) o;
        return countTenthTicketDiscount == that.countTenthTicketDiscount &&
                countBirthdayDiscount == that.countBirthdayDiscount &&
                Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, countTenthTicketDiscount, countBirthdayDiscount);
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

        private DiscountCountInfo newDiscountInfo;

        public Builder() {
            newDiscountInfo = new DiscountCountInfo();
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

        public DiscountCountInfo build() {
            return newDiscountInfo;
        }
    }
}
