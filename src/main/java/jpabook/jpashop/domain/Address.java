package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){

    }

    public Address(String city, String street, String zipcode) { //하나씩 getter setterㅁㄴ들어주기보다는 그냥 담아서 넣어주기..
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;

    }
}
