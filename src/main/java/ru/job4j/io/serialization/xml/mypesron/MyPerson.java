package ru.job4j.io.serialization.xml.mypesron;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyPerson {

    @XmlAttribute
    private String name;

    private boolean sex;

    private int age;

    private String status;

    private ContactPhone contactPhone;

    @XmlElementWrapper(name = "autos")
    @XmlElement(name = "auto")
    private String[] autos;

    public MyPerson() { }

    public MyPerson(String name, boolean sex, int age, String status,
                    ContactPhone contactPhone, String... autos) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.status = status;
        this.contactPhone = contactPhone;
        this.autos = autos;
    }

    public static void main(String[] args) throws JAXBException {

        final MyPerson person = new MyPerson("Alex", false, 30, "student",
                new ContactPhone("11-111"), "Ford", "DAF");

        JAXBContext context = JAXBContext.newInstance(MyPerson.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }

    @Override
    public String toString() {
        return "MyPerson{"
                + "name='" + name + '\''
                + ", sex=" + sex
                + ", age=" + age
                + ", status='" + status + '\''
                + ", contactPhone=" + contactPhone
                + ", autos=" + Arrays.toString(autos)
                + '}';
    }
}
