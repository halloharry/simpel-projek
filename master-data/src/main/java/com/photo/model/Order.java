package com.photo.model;

import com.photo.model.base.ModelBase;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tbl_order")
public class Order extends ModelBase {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "is_cancel")
    private Boolean isCancel;

}
