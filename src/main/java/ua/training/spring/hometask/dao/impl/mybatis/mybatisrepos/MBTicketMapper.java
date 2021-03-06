package ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.domain.Ticket;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MBTicketMapper {

    @Insert("INSERT INTO tickets (user_id, event_id, date_time, seat, base_price) "
            + "VALUES (#{user.id},#{event.id},#{dateTime},#{seat},#{basePrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(Ticket object);

    @Delete("DELETE FROM tickets WHERE id = #{id}")
    void remove(Ticket id);

    @Select("SELECT * FROM tickets WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateTime", column = "date_time"),
            @Result(property = "seat", column = "seat"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.getById")),
            @Result(property = "event", column = "event_id", javaType = Event.class,
                    one = @One(select = "ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getById"))})
    Ticket getById(@Param("id") Long id);

    @Select("SELECT * FROM tickets t "
            + "LEFT JOIN users u ON t.user_id=u.id "
            + "LEFT JOIN events e ON t.event_id=e.id")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateTime", column = "date_time"),
            @Result(property = "seat", column = "seat"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.getById")),
            @Result(property = "event", column = "event_id", javaType = Event.class,
                    one = @One(select = "ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getById"))})
    Collection<Ticket> getAll();

    @Select("SELECT * FROM tickets t "
            + "LEFT JOIN users u ON t.user_id=u.id "
            + "LEFT JOIN events e ON t.event_id=e.id where t.user_id is not null and t.event_id = #{id} and t.date_time = #{dateTime}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "dateTime", column = "date_time"),
            @Result(property = "seat", column = "seat"),
            @Result(property = "basePrice", column = "base_price"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBUserMapper.getById")),
            @Result(property = "event", column = "event_id", javaType = Event.class,
                    one = @One(select = "ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos.MBEventMapper.getById"))})
    Collection<Ticket> getPurchasedTicketsForEvent(@Param("id") Long id, @Param("dateTime") LocalDateTime dateTime);
}
