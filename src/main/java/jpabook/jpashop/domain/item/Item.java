package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnouthStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



//ddd 방식으로써 domain이 주로 생각하고,,,,,,,, 그렇기 때문에 item 때문에
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //단일테이블 전략 : 서비스 규모가 크지않고 조인전략을 선택해서 복잡하게 갈 필요가 없을때 하는거다!
@DiscriminatorColumn(name="dType" ) //부모클래스로 선언하는거......... 하위 클래스를 구분하는 용도의 컬이다!
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
