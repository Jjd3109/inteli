package jpabook.jpashop.domain.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // db까지 연결해서 확인해주기 위해서 ....... 만들어주는거 runwith + springBootTest 엮어서!!
@SpringBootTest //
@Transactional // 이게 있어야 실패~ 롤백
public class MemberServiceTest {

    @Autowired // memberservice를 참조해서 해야 되기때문에 !!
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em; // 엔티티 전부 관리해주는거!



    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long SaveId = memberService.join(member);


        //then
        em.flush(); //
        assertEquals(member, memberRepository.findOne(SaveId)); //assertEquals .. 안에 있는 두 객체에 같은지 안같은지 확인 !!
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when

        memberService.join(member1);
        memberService.join(member2);



        //then
        fail("예외가 발생해야합니다");
    }

    @Test
    public void 테스트(){
//        Map<String, Object> test1 = new HashMap<>();
//        Object[] test2 = new Object[2];
//        test2[0] = 1;
//        test2[1] = 2;
//        System.out.println("test2 값" + test2.toString());
//
//        test1.put("1번자리", test2);
//        test1.put("2번자리", test2);
//
//        System.out.println("test1.get(\"1번자리\").toString() 번자리 + " + test1.get("1번자리").getClass().toString());


        Map<String, Object> test1 = new HashMap<>();

        Map<String, Object> test2 = new HashMap<>();
        test2.put("나는", "바보다");
        test2.put("2번나는", "진짜바보다");

        test1.put("1번자리", test2);
        test1.put("2번자리", test2);

        for(String key : test2.keySet()){
            System.out.println("키값 :" + key);
            System.out.println("밸류값 : " + test2.get(key));
        }


    }


}