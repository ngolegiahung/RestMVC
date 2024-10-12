package com.springrest.MVCTest.api.v1.mapper;

import com.springrest.MVCTest.api.v1.model.CategoryDTO;
import com.springrest.MVCTest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper Instance = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
