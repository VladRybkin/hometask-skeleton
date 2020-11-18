package ua.training.spring.hometask.dto.soap.response;

import ua.training.spring.hometask.dto.soap.request.UserResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserByEmailResponse", namespace = "http://localhost:8888/schemas/users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class GetUserByEmailResponse {

    @XmlElement
    private UserResponse userResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
