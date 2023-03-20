package com.dbmigration.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card_details")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_detail_id")
    private int cardDetailId;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "card_number")
    private String cardNumber;
}
