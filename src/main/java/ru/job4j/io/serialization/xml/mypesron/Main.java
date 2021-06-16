package ru.job4j.io.serialization.xml.mypesron;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        MyPerson person = new MyPerson("Alex", false, 30,
                "student", new ContactPhone("11-111"), "Ford", "DAF");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(MyPerson.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            MyPerson result = (MyPerson) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
