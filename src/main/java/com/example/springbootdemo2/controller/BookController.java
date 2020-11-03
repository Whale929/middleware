package com.example.springbootdemo2.controller;

import com.example.springbootdemo2.entity.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    private static final Logger log= LoggerFactory.getLogger(BookController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void one(Book book) throws JsonProcessingException {
        log.info("----开始----");
        final String content=objectMapper.writeValueAsString(book);
        final String key="redis:template:string";
        ValueOperations valueOperations=redisTemplate.opsForValue();
        /*log.info("写入缓存中的内容：{} ",content);
        valueOperations.set(key,content);*/
        Object result=valueOperations.get(key);
        log.info("读取出来的内容：{}",result);
    }

    @RequestMapping(value = "info",method = RequestMethod.GET)
    public Book Info(Integer bookNo,String bookName) throws JsonProcessingException {
        Book book=new Book();
        book.setBookNo(bookNo);
        book.setName(bookName);
        this.one(book);
        return book;
    }
}
