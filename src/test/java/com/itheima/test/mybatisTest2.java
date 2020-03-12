package com.itheima.test;

import com.itheima.dao.Bookdao;
import com.itheima.domain.Books;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mybatisTest2 {
    private InputStream in;
    private SqlSession sqlSession;
    private Bookdao bookdao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("sqlMAppconfig.xml");
        //2.获取sqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3.使用sqlSessionfactory获得一个sqlsesssion对象
        sqlSession = sqlSessionFactory.openSession();
        //4.使用sqlsession创建dao的代理对象
        bookdao=sqlSession.getMapper(Bookdao.class);
    }
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }
    @Test
    public void insertBook(){
        //A、新增一本图书
        Books books=new Books();
        books.setBname("java算法");
        books.setPress("北京出版社");
        books.setPubdate(new Date());
        books.setMoney(52D);
        books.setAuthor("lsd");
        bookdao.insertBook(books);
    }
    @Test
    public void updateBooks() throws ParseException {
        // B、修改图书信息（根据编号） ，修改图书的出版日期和图书名
        Map<String,Object> map=new HashMap<String, Object>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date= sd.parse("2011-10-01");
        String name="好书";
        int id=2;
        map.put("bid",id);
        map.put("pdate",date);
        map.put("bname",name);
        bookdao.updateBooks(map);
    }
    @Test
    public void DeleteBooks(){
        // C、根据编号删除一本图书
        bookdao.deleteBook(1);
    }
    @Test
    public void selectBooks(){
        // D、根据图书名称模糊查询图书信息
        String name="安%";
        Books books=bookdao.selectBooklike(name);
        System.out.println(books);
    }
    @Test
    public void selectBooksDate(){
        // E、找出出版日期在2001-2019之间的书籍（初始化数据请自行新增)
        List<Books> list=bookdao.findyeatBook();
        for(Books b:list){
            System.out.println(b);
        }
    }
    @Test
    public void selectBooksavg(){
        //  F、找出售价大于所有图书平均价的书籍。
        List<Books> list=bookdao.selectBookavg();
        for(Books b:list){
            System.out.println(b);
        }
    }
}
