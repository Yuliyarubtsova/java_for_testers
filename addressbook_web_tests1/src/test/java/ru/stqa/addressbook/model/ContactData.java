package ru.stqa.addressbook.model;

public record ContactData(
        String id,
        String lastname,
        String firstname,
        String address,
        String home,
        String mobile,
        String work,
        String email,
        String email2,
        String email3,
        String homepage,
        String photo) {

    public ContactData() {
        this("", "","","", "", "", "", "", "", "", "","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.lastname, this.firstname, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, lastname, this.firstname, this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, this.lastname, firstname,  this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastname, this.firstname,  address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, home, this.mobile, this.work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, mobile, this.work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, this.mobile, work, this.email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, this.mobile, this.work, email, this.email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, this.mobile, this.work, this.email, email2, this.email3, this.homepage, this.photo);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, this.mobile, this.work, this.email, this.email2, email3, this.homepage, this.photo);
    }
    public ContactData withHomepage(String homepage) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, homepage, this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.home, this.mobile, this.work, this.email, this.email2, this.email3, this.homepage, photo);
    }

}