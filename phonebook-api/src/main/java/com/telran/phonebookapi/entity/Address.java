package com.telran.phonebookapi.entity;


import lombok.*;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String country;
    @Setter
    private String city;
    @Setter
    private String index;
    @Setter
    private String address;
    @Setter
    private String homeNr;
    @Setter
    private boolean isFavorite;

    @ManyToOne
    private Contact contact;

    public Address(@NonNull String country, @NonNull String city, @NonNull String index, @NonNull String address, @NonNull String homeNr, boolean isFavorite, @NonNull Contact contact) {
        this.country = country;
        this.city = city;
        this.index = index;
        this.address = address;
        this.homeNr = homeNr;
        this.isFavorite = isFavorite;
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) && Objects.equals(country, address1.country) && Objects.equals(city, address1.city) && Objects.equals(index, address1.index) && Objects.equals(address, address1.address) && Objects.equals(homeNr, address1.homeNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, index, address, homeNr);
    }
}
