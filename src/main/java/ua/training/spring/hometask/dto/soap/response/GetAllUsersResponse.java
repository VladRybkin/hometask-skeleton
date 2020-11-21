package ua.training.spring.hometask.dto.soap.response;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "getAllUsersResponse", namespace = "http://training/schemas/hometask")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class GetAllUsersResponse {

    private List<UserResponse> users = new ArrayList<>();

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("users", users)
                .toString();
    }
}
