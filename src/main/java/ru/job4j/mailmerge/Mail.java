package ru.job4j.mailmerge;

public class Mail {
    private String mail;
    private String userName;

    public Mail(String mail, String userName) {
        this.mail = mail;
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public String getUserName() {
        return userName;
    }
}
