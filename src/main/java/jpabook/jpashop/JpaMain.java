package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setName("John");
            em.persist(member);

            System.out.println("======================");

            System.out.println("member = " + member.getName());

            System.out.println("======================");

            Order order = new Order();
            order.addOrderItem(new OrderItem());

            tx.commit(); //커밋시 SQL문 나감
        } catch (Exception e) {
            tx.rollback();//에러 시 롤백
        } finally {
            em.close();//닫기
        }

        emf.close();
    }
}
