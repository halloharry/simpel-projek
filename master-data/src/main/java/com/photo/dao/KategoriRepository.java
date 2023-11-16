package com.photo.dao;

import com.photo.model.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KategoriRepository extends JpaRepository<Kategori, Long> {

    @Query(value = "select * from kategori k where k.name = ?1 ", nativeQuery = true)
    List<Kategori> findAllByNameLike(String name);
}
