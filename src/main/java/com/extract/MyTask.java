package com.extract;

import com.dao.AddBook;
import com.dao.Book;

import java.sql.SQLException;

public class MyTask extends Thread {
    private  int threadnum;
    private  String url;
    private static AddBook addBook=new AddBook();
    private static Extract extract=new Extract();
     public MyTask(int threadnum,String url){
        this.threadnum=threadnum;
        this.url=url;
    }
    @Override
    public void run(){
         synchronized (this){
             Book book=new Book();
             book=extract.regexMain(url);
             try{
                 //把爬取到的数据存入数据库
                 addBook.add(book);
             }catch (SQLException e){
                 e.printStackTrace();
             }
         }
    }
}
