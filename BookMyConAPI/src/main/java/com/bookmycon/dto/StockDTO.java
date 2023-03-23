package com.bookmycon.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class StockDTO {

    private  int productId;

    private int stockValue;
}
