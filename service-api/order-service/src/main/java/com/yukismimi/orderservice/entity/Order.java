package com.yukismimi.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "order_")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long userId;
    private long couponId;
    private String orderSn;
    private java.sql.Timestamp createTime;
    private String username;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private BigDecimal freightAmount;
    private BigDecimal promotionAmount;
    private BigDecimal integrationAmount;
    private BigDecimal couponAmount;
    private BigDecimal discountAmount;
    private int payType;
    private int sourceType;
    //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
    private int status;
    private int orderType;
    private String deliveryCompany;
    private String deliverySn;
    private int autoConfirmDay;
    private int integration;
    private int growth;
    private String promotionInfo;
    private int billType;
    private String billHeader;
    private String billContent;
    private String billReceiverPhone;
    private String billReceiverEmail;
    private String receiverName;
    private String receiverPhone;
    private String receiverPostCode;
    private String receiverProvince;
    private String receiverCity;
    private String receiverRegion;
    private String receiverDetailAddress;
    private String note;
    private int confirmStatus;
    private int deleteStatus;
    private int useIntegration;
    private java.sql.Timestamp paymentTime;
    private java.sql.Timestamp deliveryTime;
    private java.sql.Timestamp receiveTime;
    private java.sql.Timestamp commentTime;
    private java.sql.Timestamp modifyTime;

    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItemList;
}
