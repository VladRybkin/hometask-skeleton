package ua.training.spring.hometask.domain;

import com.google.common.base.Objects;

import java.util.Collection;
import java.util.Collections;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


public class Auditorium {

    private String name;

    private long numberOfSeats;

    private Set<String> vipSeats = Collections.emptySet();

    public Auditorium() {
    }

    public Auditorium(String name, long numberOfSeats, Set<String> vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
        this.vipSeats = vipSeats;
    }

    /**
     * Counts how many vip seats are there in supplied <code>seats</code>
     *
     * @param seats Seats to process
     * @return number of vip seats in request
     */
    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(String.valueOf(seat))).count();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Long> getAllSeats() {
        return LongStream.range(1, numberOfSeats + 1).boxed().collect(Collectors.toSet());
    }

    public Set<String> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<String> vipSeats) {
        this.vipSeats = vipSeats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auditorium that = (Auditorium) o;
        return numberOfSeats == that.numberOfSeats &&
                Objects.equal(name, that.name) &&
                Objects.equal(vipSeats, that.vipSeats);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name, numberOfSeats, vipSeats);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("numberOfSeats", numberOfSeats)
                .add("vipSeats", vipSeats)
                .toString();
    }
}
