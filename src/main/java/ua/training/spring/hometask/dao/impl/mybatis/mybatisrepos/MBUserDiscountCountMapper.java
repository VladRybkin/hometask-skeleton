package ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.UserDiscountCount;

import java.util.Collection;

public interface MBUserDiscountCountMapper {

    @Insert("INSERT INTO user_discount_counts (name, count_tenth_ticket_discount, count_birthday_discount) "
            + "VALUES (#{name},#{countTenthTicketDiscount},#{countBirthdayDiscount})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(UserDiscountCount object);

    @Delete("DELETE FROM user_discount_counts WHERE id = #{id}")
    void remove(UserDiscountCount id);

    @Select("SELECT * FROM user_discount_counts WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "countTenthTicketDiscount", column = "count_tenth_ticket_discount"),
            @Result(property = "countBirthdayDiscount", column = "count_birthday_discount")})
    UserDiscountCount getById(@Param("id") Long id);

    @Select("SELECT * FROM user_discount_counts")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "countTenthTicketDiscount", column = "count_tenth_ticket_discount"),
            @Result(property = "countBirthdayDiscount", column = "count_birthday_discount")})
    Collection<UserDiscountCount> getAll();
}
