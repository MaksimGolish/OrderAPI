package com.example.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Customer {
    public @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    private String firstName;
    private String lastName;
    private String phone;

    public Customer(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Customer() {
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setName(String fullName) {
        String[] parts = fullName.split(" ");
        firstName = parts[0];
        lastName = parts[1];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                firstName.equals(customer.firstName) &&
                phone.equals(customer.phone) &&
                lastName.equals(customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, phone, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", role='" + phone + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
