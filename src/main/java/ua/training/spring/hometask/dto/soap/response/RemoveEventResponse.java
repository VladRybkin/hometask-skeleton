package ua.training.spring.hometask.dto.soap.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "removeEventResponse", namespace = "http://training/schemas/hometask")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class RemoveEventResponse {

    @XmlElement
    private boolean removed;

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
