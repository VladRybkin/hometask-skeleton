package ua.training.spring.hometask.dto.soap.request;

import ua.training.spring.hometask.domain.EventCount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllUsersRequest", namespace = "http://localhost:8888/schemas/users")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class GetAllUsersRequest {


}
