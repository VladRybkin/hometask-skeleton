package ua.training.spring.hometask.dao.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.User;

import java.util.Collection;

public interface MBUserMapper {

    @Insert("INSERT INTO users (first_name, last_name, email, date_of_birth) "
            + "VALUES (#{firstName},#{lastName},#{email},#{dateOfBirth})")
    long save(User object);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void remove(User id);

    @Select("SELECT * FROM event_counts WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "dateOfBirth", column = "date_of_birth")})
    User getById(@Param("id") Long id);

    @Select("SELECT * FROM users")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "dateOfBirth", column = "date_of_birth")})
    Collection<User> getAll();
}
