package ua.training.spring.hometask.dao.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.Event;

import java.util.Collection;

public interface MBEventMapper {

    @Insert("INSERT INTO events (name, base_price, rating) "
            + "VALUES (#{name},#{basePrice},#{rating})")
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

    @Select("SELECT * FROM events")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "rating", column = "rating")})
    Collection<Event> getAll();
}
