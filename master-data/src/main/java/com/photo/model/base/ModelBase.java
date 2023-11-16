package com.photo.model.base;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class ModelBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
