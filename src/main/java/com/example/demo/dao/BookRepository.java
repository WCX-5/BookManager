package com.example.demo.dao;

import com.example.demo.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Integer> {
    //使用符合命名规范的方法名称进行查询
    public List<Book> findByAuthor(String author);
    public List<Book> findByCategoryAndAuthor(String category, String author);
    public List<Book> findByPriceBetween(double minPrice, double maxcPrice);
    public List<Book> findByNameLike(String name);

    //使用@Query注解，自定义条件查询//查询某个类别的书
    @Query("{'category':?0}")
    public List<Book> findCategory(String category);

    //查询价格小于某个值的书
    @Query("{'price':{'$lte':?0}}")
    public List<Book> findPrice(double price);

    //模糊查询名字,以及类别,模糊查询用$regex关键字
    @Query("{'name':{$regex:/?0/}, 'category':?1}")
    public List<Book> searchBooks(String name, String category);
}