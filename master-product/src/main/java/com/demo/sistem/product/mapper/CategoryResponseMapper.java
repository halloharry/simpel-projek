package com.demo.sistem.product.mapper;

import com.photo.dto.response.CategoryResponseDto;
import com.photo.model.Kategori;
import com.photo.util.mapper.ADATAMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper extends ADATAMapper<Kategori, CategoryResponseDto> {
    @Override
    public CategoryResponseDto convert(Kategori kategori) {
       return CategoryResponseDto.builder().name(kategori.getName()).build();
    }
}
