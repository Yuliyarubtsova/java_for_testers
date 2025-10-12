package model;

public record ContactData(String id, String firstname, String lastname, String address, String mobile, String email, String homepage) {

    public ContactData() {
        this("", "","","","","","");
    }


    public ContactData withId(String id) {
        return new ContactData(id, this.lastname, this.firstname, this.address, this.mobile, this.email, this.homepage);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, lastname, this.firstname, this.address, this.mobile, this.email, this.homepage);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.mobile, this.email, this.homepage);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.mobile, this.email, this.homepage);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, mobile, this.email, this.homepage);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, email, this.homepage);
    }

    public ContactData withHomepage(String homepage) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, homepage);
    }

}