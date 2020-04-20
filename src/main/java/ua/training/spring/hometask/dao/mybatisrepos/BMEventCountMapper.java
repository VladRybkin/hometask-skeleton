package ua.training.spring.hometask.dao.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;

public interface BMEventCountMapper {

    @Insert("INSERT INTO event_counts (name, count_get_by_name, count_book_tickets, count_get_price) "
            + "VALUES (#{eventName},#{countGetByName},#{countBookTickets},#{countGetPrice})")
    long save(EventCount object);

    @Delete("DELETE FROM event_counts WHERE id = #{id}")
    void remove(EventCount id);

    @Select("SELECT * FROM event_counts WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "eventName", column = "name"),
            @Result(property = "countGetByName", column = "count_get_by_name"),
            @Result(property = "countBookTickets", column = "count_book_tickets"),
            @Result(property = "countGetPrice", column = "count_get_price")})
    EventCount getById(@Param("id") Long id);

    @Select("SELECT * FROM event_counts")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "eventName", column = "name"),
            @Result(property = "countGetByName", column = "count_get_by_name"),
            @Result(property = "countBookTickets", column = "count_book_tickets"),
            @Result(property = "countGetPrice", column = "count_get_price")})
    Collection<EventCount> getAll();
}
