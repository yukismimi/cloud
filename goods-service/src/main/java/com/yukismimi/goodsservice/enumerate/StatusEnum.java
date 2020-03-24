package com.yukismimi.goodsservice.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

    SELLING(1, "销售中"),
    ON_SALE(2, "特惠"),
    ADVANCE_BOOK(3, "预售"),
    SELL_OUT(4, "售罄");

    private int code;
    private String status;
}
