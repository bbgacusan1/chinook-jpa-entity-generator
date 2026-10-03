package edu.gcu.cst339.chinook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @NotNull
    @Size(max = 40)
    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;

    @NotNull
    @Size(max = 20)
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Size(max = 80)
    @Column(name = "company", length = 80)
    private String company;

    @Size(max = 70)
    @Column(name = "address", length = 70)
    private String address;

    @Size(max = 40)
    @Column(name = "city", length = 40)
    private String city;

    @Size(max = 40)
    @Column(name = "state", length = 40)
    private String state;

    @Size(max = 40)
    @Column(name = "country", length = 40)
    private String country;

    @Size(max = 10)
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Size(max = 24)
    @Column(name = "phone", length = 24)
    private String phone;

    @Size(max = 24)
    @Column(name = "fax", length = 24)
    private String fax;

    @NotNull
    @Size(max = 60)
    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_rep_id")
    private Employee supportRep;

    protected Customer() {
    }

    Integer getCustomerId() {
        return customerId;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getCompany() {
        return company;
    }

    void setCompany(String company) {
        this.company = company;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
    }

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    String getPostalCode() {
        return postalCode;
    }

    void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    String getFax() {
        return fax;
    }

    void setFax(String fax) {
        this.fax = fax;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    Employee getSupportRep() {
        return supportRep;
    }

    void setSupportRep(Employee supportRep) {
        this.supportRep = supportRep;
    }
}
