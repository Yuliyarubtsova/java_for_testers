package model;

import java.util.Objects;

public record ContactData(String firstname, String lastname, String address, String mobile, String email, String homepage) {

    public ContactData() {
        this("","","","","","");
    }


    public ContactData withLastname(String ivanovich) {
        return new ContactData(ivanovich, this.firstname, this.address, this.mobile, this.email, this.homepage);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.mobile, this.email, this.homepage);
    }
    public ContactData withAddress(String Address) {
        return new ContactData(this.firstname, this.lastname, Address, this.mobile, this.email, this.homepage);
    }

}