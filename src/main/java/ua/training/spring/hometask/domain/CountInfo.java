package ua.training.spring.hometask.domain;


import com.google.common.base.Objects;

public class CountInfo {

    private long id;

    private long getByNameCount;

    private long bookTicketsCount;

    private long getPriceCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGetByNameCount() {
        return getByNameCount;
    }

    public void setGetByNameCount(long getByNameCount) {
        this.getByNameCount = getByNameCount;
    }

    public long getBookTicketsCount() {
        return bookTicketsCount;
    }

    public void setBookTicketsCount(long bookTicketsCount) {
        this.bookTicketsCount = bookTicketsCount;
    }

    public long getGetPriceCount() {
        return getPriceCount;
    }

    public void setGetPriceCount(long getPriceCount) {
        this.getPriceCount = getPriceCount;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("getByNameCount", getByNameCount)
                .add("bookTicketsCount", bookTicketsCount)
                .add("getPriceCount", getPriceCount)
                .toString();
    }
}
