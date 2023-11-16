package com.demo.sistem.product.service.kategori;

import com.photo.dto.request.CategoryRequestDto;
import com.photo.dto.response.CategoryResponseDto;
import com.photo.util.exceptionn.UserCustomExeption;

public interface KategoriService {
    CategoryResponseDto addKategori(CategoryRequestDto categoryRequestDto) throws UserCustomExeption;
}
