package org.example.questao4.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class User {
    private final String name;
    private final String email;
    private final List<Address> addresses;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.addresses = new ArrayList<>();
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void addAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        addresses.add(address);
    }
}
