package model;

public record ContactData(String id, String lastname,  String firstname, String address, String mobile, String email, String homepage, String photo) {

    public ContactData() {
        this("", "","","","","","","");
    }


    public ContactData withId(String id) {
        return new ContactData(id, this.lastname, this.firstname, this.address, this.mobile, this.email, this.homepage, this.photo);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, lastname, this.firstname, this.address, this.mobile, this.email, this.homepage, this.photo);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, this.lastname, firstname,  this.address, this.mobile, this.email, this.homepage, this.photo);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastname, this.firstname,  address, this.mobile, this.email, this.homepage, this.photo);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, mobile, this.email, this.homepage, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.mobile, email, this.homepage, this.photo);
    }

    public ContactData withHomepage(String homepage) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.mobile, this.email, homepage, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastname, this.firstname,  this.address, this.mobile, this.email,this.homepage, photo);
    }

}