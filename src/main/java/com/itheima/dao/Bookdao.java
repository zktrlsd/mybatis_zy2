package com.itheima.dao;

import com.itheima.domain.Books;

import java.util.List;
import java.util.Map;

public interface Bookdao {
    /**
     * 新增加一本书
     *
     * @param books
     */
    void insertBook(Books books);

    /**
     * 修改图书的出版日期和图书名
     * @param map
     */
    void updateBooks( Map<String,Object> map);

    /**
     * 根据id删除书
     * @param id
     */
    void deleteBook(int id);

    /**
     * 根据书名模糊查询
     * @param name
     */
    Books selectBooklike(String name);

    /**
     * 查询大于平均价格的图书
     * @return
     */
    List<Books> selectBookavg();

    /**
     * 查询年份在2001到2019的
     * @return
     */
    List<Books> findyeatBook();
}
