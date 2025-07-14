package com.cmcu.bookmanager.controller;

import com.cmcu.bookmanager.model.Book;
import com.cmcu.bookmanager.repository.BookRepository;

// import ch.qos.logback.classic.Logger; // Removed, not needed

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepo;

    @GetMapping
    public String listBooks(@RequestParam(required = false, defaultValue = "none") String sort, Model model) {
        logger.info("Sort parameter received: {}", sort);
        List<Book> books;
        if ("asc".equalsIgnoreCase(sort)) {
            books = bookRepo.findAllByOrderByPriceAsc();
        } else if ("desc".equalsIgnoreCase(sort)) {
            books = bookRepo.findAllByOrderByPriceDesc();
        } else {
            books = bookRepo.findAll();
        }
        logger.info("Books retrieved: {} books", books.size());
        books.forEach(book -> logger.info("Book: {} - Price: {}", book.getTitle(), book.getPrice()));
        model.addAttribute("books", books);
        model.addAttribute("currentSort", sort);
        return "books";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookRepo.findById(id).orElse(null));
        return "edit-book";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        bookRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepo.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String keyword, Model model) {
        List<Book> books = bookRepo.findByTitleContainingIgnoreCase(keyword);
        model.addAttribute("books", books);
        return "books";
    }

    // REST API
    @RestController
    @RequestMapping("/api/books")
    public static class BookRestController {
        @Autowired
        private BookRepository bookRepo;

        @GetMapping
        public List<Book> getAll() {
            return bookRepo.findAll();
        }

        @PostMapping
        public Book create(@RequestBody Book book) {
            return bookRepo.save(book);
        }

        @PutMapping("/{id}")
        public Book update(@PathVariable Long id, @RequestBody Book book) {
            book.setId(id);
            return bookRepo.save(book);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            bookRepo.deleteById(id);
        }

        @GetMapping("/search")
        public List<Book> search(@RequestParam String keyword) {
            return bookRepo.findByTitleContainingIgnoreCase(keyword);
        }

        @GetMapping("/sort")
        public List<Book> sortBooks(@RequestParam String order) {
            if ("asc".equalsIgnoreCase(order)) {
                return bookRepo.findAllByOrderByPriceAsc();
            } else if ("desc".equalsIgnoreCase(order)) {
                return bookRepo.findAllByOrderByPriceDesc();
            }
            return bookRepo.findAll();
        }
    }
}
