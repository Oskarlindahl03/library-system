package com.example.library_system.Dto;

public class BookWithLimitedDetailsDTO {
        private Long bookId;
        private String title;
        private Integer publicationYear;
        private Integer availableCopies;
        private Integer totalCopies;
        private AuthorDTO author;

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getPublicationYear() {
            return publicationYear;
        }

        public void setPublicationYear(Integer publicationYear) {
            this.publicationYear = publicationYear;
        }

        public Integer getAvailableCopies() {
            return availableCopies;
        }

        public void setAvailableCopies(Integer availableCopies) {
            this.availableCopies = availableCopies;
        }

        public Integer getTotalCopies() {
            return totalCopies;
        }

        public void setTotalCopies(Integer totalCopies) {
            this.totalCopies = totalCopies;
        }

        public AuthorDTO getAuthor() {
            return author;
        }

        public void setAuthor(AuthorDTO author) {
            this.author = author;
        }
    }

