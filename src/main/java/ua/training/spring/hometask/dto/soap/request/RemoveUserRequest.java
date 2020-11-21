package ua.training.spring.hometask.dto.soap.request;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "removeUserRequest", namespace = "http://training/schemas/hometask")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class RemoveUserRequest {

    @XmlElement
    private Long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
