package ua.training.spring.hometask.dto.soap.response;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "removeUserResponse", namespace = "http://localhost:8888/schemas/users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class RemoveUserResponse {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
