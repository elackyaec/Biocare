package com.retail.biocare.Models;

public class UserDetailsModel {

    private String CustomerID, FirstName, LastName, DateofBirth, Gender, Marital, address1, address2, city, state, zip, country, Phone, Email, Nominee, Relation, NomineeaAge, KeyValue;

    public UserDetailsModel(String customerID, String firstName, String lastName, String dateofBirth, String gender, String marital, String address1, String address2, String city, String state, String zip, String country, String phone, String email, String nominee, String relation, String nomineeaAge, String keyValue) {
        CustomerID = customerID;
        FirstName = firstName;
        LastName = lastName;
        DateofBirth = dateofBirth;
        Gender = gender;
        Marital = marital;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        Phone = phone;
        Email = email;
        Nominee = nominee;
        Relation = relation;
        NomineeaAge = nomineeaAge;
        KeyValue = keyValue;
    }


    public String getCustomerID() {
        return CustomerID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public String getGender() {
        return Gender;
    }

    public String getMarital() {
        return Marital;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getNominee() {
        return Nominee;
    }

    public String getRelation() {
        return Relation;
    }

    public String getNomineeaAge() {
        return NomineeaAge;
    }

    public String getKeyValue() {
        return KeyValue;
    }


}
