package com.demo.sistem.product.endpoint;

import com.demo.sistem.product.service.kategori.KategoriService;
import com.photo.dto.request.CategoryRequestDto;
import com.photo.dto.response.CategoryResponseDto;
import com.photo.util.IResultDto;
import com.photo.util.core.APIResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/kategori")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KategoriEndpoint {

    private final KategoriService kategoriService;

    @PostMapping("/add-kategori")
    public IResultDto<CategoryResponseDto> addKategori(@RequestBody CategoryRequestDto categoryRequestDto, HttpServletRequest request){
        try {
            return APIResponseBuilder.ok(kategoriService.addKategori(categoryRequestDto));
        } catch (Exception e) {
           return APIResponseBuilder.internalServerError(null, e, e.getMessage(), request);
        }
    }
}
