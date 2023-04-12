package jpabook.jpashop.domain.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor //리콰이어드아규컨스트럭트를 사용해줌으로써 서비스단에랑
public class MemberRepository {
    //dao역할을 해준다.............
    //dto는 domain에 다적어주고.......


//    @PersistenceContext // entity를 달고있는거를 전부관리해주는 것
//    private EntityManager em;  // 처음에는 이걸로 구성했으나 일관성있게 하기위해서 requiredArgsConstructor 를 사용해서 만들어주기

    private final EntityManager em;



    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
        //jpql이다... sql과 다르다 entity 객체로 준다
        //qlString ........ > member < 값을 가져와서 * 안써도 된다!!!!!!!!!!!!!!
    }

    public List<Member> findMember(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name)
                .getResultList();
    }

}
