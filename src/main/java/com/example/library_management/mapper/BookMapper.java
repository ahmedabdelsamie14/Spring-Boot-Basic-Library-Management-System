package com.example.library_management.mapper;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book toBook(BookDTO bookDTO);

    BookDTO toBookDTO(Book book);
}
