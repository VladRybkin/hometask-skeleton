package ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MBEventMapper {

    @Insert("INSERT INTO events (name, base_price, rating) "
            + "VALUES (#{name},#{basePrice},#{rating})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(Event object);

    @Delete("DELETE events WHERE id = #{id}")
    void remove(Event id);

    @Select("SELECT * FROM events WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "rating", column = "rating")})
    Event getById(@Param("id") Long id);

    @Select("SELECT * FROM events WHERE name = #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "rating", column = "rating")})
    Event getByName(@Param("name") String name);

    @Select("SELECT * FROM events ev "
            + "LEFT JOIN event_dates ed ON ev.id=ed.event_id "
            + "LEFT JOIN air_dates ai ON ed.air_date_id=ai.id WHERE ai.event_date BETWEEN #{from} AND #{to}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "rating", column = "rating")})
    Event getForDateRange(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Select("SELECT * FROM events ev " +
            "LEFT JOIN event_dates ed ON ev.id=ed.event_id " +
            "LEFT JOIN air_dates ai ON ed.air_date_id=ai.id WHERE ai.event_date < #{to}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "rating", column = "rating")})
    Event getNextEvents(@Param("to") LocalDateTime to);

    @Select("SELECT * FROM events")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "rating", column = "rating")})
    Collection<Event> getAll();
}
