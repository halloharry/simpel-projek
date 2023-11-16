package com.demo.sistem.product.service.kategori.impl;

import com.demo.sistem.product.mapper.CategoryResponseMapper;
import com.demo.sistem.product.service.kategori.KategoriService;
import com.photo.dao.KategoriRepository;
import com.photo.dto.request.CategoryRequestDto;
import com.photo.dto.response.CategoryResponseDto;
import com.photo.model.Kategori;
import com.photo.util.exceptionn.UserCustomExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KategoriImpl implements KategoriService {

    private final KategoriRepository kategoriRepository;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public CategoryResponseDto addKategori(CategoryRequestDto categoryRequestDto) throws UserCustomExeption {
        List<Kategori> existingCategory = kategoriRepository.findAllByNameLike(categoryRequestDto.getName())
                .stream()
                .filter(x -> !x.getIsDeleted())
                .collect(Collectors.toList());
        if (!existingCategory.isEmpty()) {
            throw new UserCustomExeption("kategori ini sudah ada");
        }
        Kategori kategori1 = new Kategori();
        kategori1.setName(categoryRequestDto.getName());
        kategori1.setIsDeleted(false);
        return categoryResponseMapper.convert(kategoriRepository.save(kategori1));
    }
}
