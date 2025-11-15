package ru.stqa.mantis.model;

public record SignUpData(String username, String password) {

    public SignUpData() {
        this("", "");
    }

    public SignUpData withUsername(String username) {
        return new SignUpData(username, this.password);
    }

    public SignUpData  withPassword(String password) {
        return new SignUpData(this.username, password);
    }
}
