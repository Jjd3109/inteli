package jpabook.jpashop.domain.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em; //전부 해준다 이거.... 꼭 기억 엔티티 매니저

    public void save(Item item){
        if(item.getId() == null){ //아이디값이 없다는 의미는 새로 만든다는 의미 !!
            em.persist(item); //데이터 저장...... . . . . . ..
        } else{
            em.merge(item); //강제로 만든다...  느낌으로 이해하기
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id); //item에서 id값...
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
        //새롭게 entitymanager를 사용해서.... jpql
    }

}
