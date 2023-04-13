package jpabook.jpashop.domain.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.exception.NotEnouthStockException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {

    @Autowired
     ItemRepository itemRepository;

    @Autowired
     MemberRepository memberRepository;

    @Autowired
     OrderService orderService;

    @Autowired
     OrderRepository orderRepository;


    @Autowired
    EntityManager em;

    @Test
    public void 상품주문() throws Exception{
        //given
        //상품주문을 했을때
        Member member = createMember(); //알트 컨트롤 m 으로 abstract 로 

        Book book = createBook("jpa고수가 되는길", 10000, 10);

        int orderCount = 2; // 2권주문

        //when
        Long orderId =orderService.order(member.getId(), book.getId(),orderCount);

        //then

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.", 8, book.getStockQuantity());
    }



    @Test(expected = NotEnouthStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;
        //when
        orderService.order(member.getId(), item.getId(), orderCount);
        //책 10권인데 9개를 주문하면 당연히 성공해야하는거아닌가..?
        //then

        fail("재고 수량 예외가 발행해야 한다.");

    }
    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Book item = createBook("테스트book", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //성공하면 1 실패하면 0 반환이다....

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus()); //두개비교!!!
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }


    private Book createBook(String name, int price, int stock) { // 커맨드 + 시프트 + t 하면 만들어준다
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stock);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        //memberRepository.save(member); 해도 되지만.. 저장만 하느는거니까 entityManger 쓰기
        em.persist(member); //영속성 컨텍스트에 저장된 상태.... db에 저장되지않고 트랜잭션의 커밋 시점에 db에 쿼리로 날라가는거
        return member;
    }
}