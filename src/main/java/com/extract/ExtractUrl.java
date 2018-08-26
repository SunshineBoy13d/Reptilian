package com.extract;

import com.dao.AddBook;
import com.dao.Book;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//爬取当前页面所有书籍的URL
public class  ExtractUrl {
    private  Extract extract=new Extract();
    private  AddBook addBook=new AddBook();
    private  Book book;
    public void start(){
        int i=0;
        //创建线程池
 //       ThreadPoolExecutor executor=new ThreadPoolExecutor(20,30,200,
   //             TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        String url="https://book.douban.com/latest?icn=index-latestbook-all";
        String result=Connection.Connet(url);
        Pattern patternUrl=Pattern.compile("<a href=\"https://book.douban.com/subject.*?>([^<]*)</a>");
        Matcher matcherUrl=patternUrl.matcher(result);
        while (matcherUrl.find()){
            String bookUrls=matcherUrl.group();
            int n=bookUrls.lastIndexOf("href=\"");
            int m=bookUrls.lastIndexOf("\">");
            String urls=bookUrls.substring(n+6,m);
            //根据提取到的URL再次提取详细书籍内容
            book= extract.regexMain(urls);
            //爬取到的内容存入数据库
            try {
               addBook.add(book);
            }catch (SQLException e){
               e.printStackTrace();
            }
          //  MyTask myTask=new MyTask(i++,urls);
          //  executor.execute(myTask);
          //  myTask.start();
        }
    }
}
