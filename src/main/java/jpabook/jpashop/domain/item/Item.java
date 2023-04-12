package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnouthStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dType" )
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany(mappedBy = "items")
    private List<Category> categoties = new ArrayList<>();

    //==비즈니스 로직 ==//
    /*
     *stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;

    }

    //==비즈니스 로직 ==//
    /*
     *stock 감소
     */
    public void removeStock(int quantity)  {
        int resStock = this.stockQuantity - quantity;
        if(resStock < 0 ){
            throw new NotEnouthStockException("need more stock");
        }

        this.stockQuantity = resStock;

    }

}
