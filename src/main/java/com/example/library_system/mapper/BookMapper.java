package com.example.library_system.mapper;

import com.example.library_system.Dto.AuthorDTO;
import com.example.library_system.Dto.BookWithLimitedDetailsDTO;
import com.example.library_system.Entity.Authors;
import com.example.library_system.Entity.Books;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookWithLimitedDetailsDTO toLimitedDTO(Books book) {
        if (book == null) return null;

        BookWithLimitedDetailsDTO dto = new BookWithLimitedDetailsDTO();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setAvailableCopies(book.getAvailableCopies());
        dto.setTotalCopies(book.getTotalCopies());

        Authors author = book.getAuthor();
        if (author != null) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setAuthorId(author.getAuthorId());
            authorDTO.setFirstName(author.getFirstName());
            authorDTO.setLastName(author.getLastName());
            dto.setAuthor(authorDTO);
        }

        return dto;
    }
}

