package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address; //embedded 안에 다잇다 하나씩 여기서 만들어주는것도 좋지만 따로 빼서 관리하는게 편해서 해준다 !!

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}
