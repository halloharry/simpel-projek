package com.photo.model;

import com.photo.model.base.ModelBase;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kategori")
public class Kategori extends ModelBase {

    @NotNull
    @Column(name = "name")
    String name;

}
