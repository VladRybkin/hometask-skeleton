package ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.EventCount;

import java.util.Collection;

public interface MBEventCountMapper {

    @Select("SELECT * FROM event_counts WHERE name = #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "eventName", column = "name"),
            @Result(property = "countGetByName", column = "count_get_by_name"),
            @Result(property = "countBookTickets", column = "count_book_tickets"),
            @Result(property = "countGetPrice", column = "count_get_price")})
    EventCount getByName(@Param("name") String name);

    @Insert("INSERT INTO event_counts (name, count_get_by_name, count_book_tickets, count_get_price) "
            + "VALUES (#{eventName},#{countGetByName},#{countBookTickets},#{countGetPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(EventCount object);

    @Insert("UPDATE event_counts SET name=#{eventName}, count_get_by_name=#{countGetByName}, " +
            "count_book_tickets=#{countBookTickets}, count_get_price=#{countGetPrice} WHERE id=#{id}")
    long update(EventCount object);

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
