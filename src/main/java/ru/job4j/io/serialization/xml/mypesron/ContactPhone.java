package ru.job4j.io.serialization.xml.mypesron;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "contactphone")
public class ContactPhone {
    @XmlAttribute
    private String phone;

    public ContactPhone() {
    }

    public ContactPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactPhone{"
                + "phone='" + phone + '\''
                + '}';
    }
}
