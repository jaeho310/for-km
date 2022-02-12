package com.example.projectsample.application.model.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @Setter
    private Long id;

    @Setter
    private int count;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Setter
    private Product product;

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;

        if (member != null) {
            member.getOrders().add(this);
        }
    }
}
