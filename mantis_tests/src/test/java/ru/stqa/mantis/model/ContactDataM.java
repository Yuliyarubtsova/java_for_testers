package ru.stqa.mantis.model;

public record ContactDataM(String username, String password, String realname, String email) {

    public ContactDataM() {
        this("", "","", "");
    }

    public ContactDataM withUsername(String username) {
        return new ContactDataM(username, this.password, this.realname, this.email);
    }
    public ContactDataM withPassword(String password) {
        return new ContactDataM(this.username, password, this.realname, this.email);
    }

    public ContactDataM withRealname(String realname) {
        return new ContactDataM(this.username, this.password, realname, this.email);
    }

    public ContactDataM withEmail(String email) {
        return new ContactDataM(this.username, this.password, this.realname, email);
    }
}
