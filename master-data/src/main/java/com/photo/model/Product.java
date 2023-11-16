package com.photo.model;

import com.photo.model.base.ModelBase;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "product")
public class Product extends ModelBase {

    @NotNull
    @Column(name = "kategori_id")
    private Long kategoriId;

    @NotNull
    @Column(name = "product_nama")
    private String productName;

    @NotNull
    @Column(name = "price")
    private Double price;

    @NotNull
    @Column(name = "amount")
    private Integer amount;

}
