package com.example.demo.controller;


import com.example.demo.dao.BookRepository;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;//基础查询（CRUD）

    @GetMapping("/books")
    public ModelAndView books() {
        List<Book> books = bookRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    @GetMapping("/book/{id}")
    public ModelAndView findStudentById(@PathVariable("id") int id) {
        Book book = (Book) bookRepository.findById(id).orElse(new Book());
        ModelAndView mv = new ModelAndView();
        mv.addObject("book", book);
        mv.setViewName("book");
        return mv;
    }

    @GetMapping("/addBook")
    public String addStudent() {
        return "addBook";
    }

    @PostMapping("/addBook")
    public ModelAndView addStudent(Book book) {
        bookRepository.insert(book);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/books");//添加成功后，跳转到查找所有学生的控制器
        return mv;
    }

    @GetMapping("/deleteBook/{id}")
    public ModelAndView deleteStudent(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/books");//删除成功后，跳转到查找所有学生的控制器
        return mv;
    }

    @GetMapping("/updateBook/{id}")
    public ModelAndView toUpdateStudent(@PathVariable("id") int id) {
        Book book = (Book) bookRepository.findById(id).orElse(new Book());
        ModelAndView mv = new ModelAndView();
        mv.addObject("book", book);
        mv.setViewName("updateBook");
        return mv;
    }

    @PostMapping("/updateBook")
    public ModelAndView UpdateStudent(Book book) {
        bookRepository.save(book);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/books");//修改成功后，跳转到查找所有学生的控制器
        return mv;
    }

    //使用符合命名规范的方法进行查询
    @GetMapping("/findByAuthor")
    public ModelAndView findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    @GetMapping("/findByCategoryAndAuthor")
    public ModelAndView findByCategoryAndAuthor(String category, String author) {
        List<Book> books = bookRepository.findByCategoryAndAuthor(category, author);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    @GetMapping("/findByPriceBetween")
    public ModelAndView findByPriceBetween(double minPrice, double maxPrice) {
        List<Book> books = bookRepository.findByPriceBetween(minPrice, maxPrice);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    @GetMapping("/findByNameLike")
    public ModelAndView findByNameLike(String name) {
        List<Book> books = bookRepository.findByNameLike(name);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    //使用@Query注解，自定义条件查询
    //查询某个类别的书
    @GetMapping("/findCategory")
    public ModelAndView findCategory(String category) {
        List<Book> books = bookRepository.findCategory(category);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    //查询价格小于某个值的书
    @GetMapping("/findPrice")
    public ModelAndView findPrice(double price) {
        List<Book> books = bookRepository.findPrice(price);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    //根据书名和目录模糊查询图书
    @GetMapping("/searchBooks")
    public ModelAndView searchBooks(String name, String category) {
        List<Book> books = bookRepository.searchBooks(name, category);
        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }
}