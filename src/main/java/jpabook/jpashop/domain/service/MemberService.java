package jpabook.jpashop.domain.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //오류가 발생했을때 원상복귀해주는거,,,,!!!!!!!!!!!!!  하나라도 실패하면 전체롤백해준다 기본내장된 프레임워크꺼를 사용해주는게 좋다
@RequiredArgsConstructor // 밑에 주석처리한 memeberService 컨스트럭트를 생성해준다 !! , @Allargument 도 있으나 리콰이어드아규컨스트럭트를 사용하는거 권장
public class MemberService {

    private final MemberRepository memberRepository; //autowired를 안쓰고 새롭게 생성자를 사용해서 하기.....

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /*
     * 회원가입
     */
    @Transactional //오류가 발생했을때 원상복귀해주는거,,,,!!!!!!!!!!!!!  하나라도 실패하면 전체롤백해준다 기본내장된 프레임워크꺼를 사용해주는게 좋다
    public Long join(Member member){
        vaildDepulicateMember(member); //중복확인 할 것...
        memberRepository.save(member);
        return member.getId(); // save하고 id값을 리턴시켜준다 !!
    }

   private void vaildDepulicateMember(Member member) { //읽어주는것만 해서 리드온니 사용 .. . . ..
        //Exception
       List<Member> findMember =  memberRepository.findMember(member.getName()); // 실무에서는 여기에서 유니크 제약조건을 걸어주는게 권장
       if(!findMember.isEmpty()){
           throw new IllegalStateException("이미 존재하는 회원입니다.");
       }

    }
    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
