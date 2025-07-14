package com.cmcu.bookmanager.repository;

import com.cmcu.bookmanager.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String keyword);

    List<Book> findByTitleContainingIgnoreCase(String keyword);

    // Thêm phương thức để sap xếp theo giá tăng dần
    List<Book> findAllByOrderByPriceAsc();

    // Thêm phương thức để sắp xếp theo giá giảm dần
    List<Book> findAllByOrderByPriceDesc();

}
