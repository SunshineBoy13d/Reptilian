package com.extract;

import com.dao.Book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {
    private  String url;
    private  int id=1;
    private  String title;
    private  String author;
    private  String press;
    private  int page;
    private Book book=new Book();
    public  Book  regexMain(String url){
        this.url=url;
        String result=Connection.Connet(url);
        return getBookInfo(result);
    }
    private  Book getBookInfo(String result){
        //提取书名
        book.setId(id++);
        Pattern patterntitle=Pattern.compile("href=\"https://img\\d.doubanio.com/view/.*?title=\"([^<]*)\">");
        Matcher matchertitle=patterntitle.matcher(result);
        while (matchertitle.find()){
            String str=matchertitle.group();
            int n=str.lastIndexOf("=\"");
            title=str.substring(n+2,str.length()-2);
            book.setTitle(title);
        }
       //提取作者
        Pattern patternauthor=Pattern.compile("<span class=\"pl\"> 作者</span>.*?\">([^<]*)</a>");
        Matcher matcherauthor=patternauthor.matcher(result);
        while (matcherauthor.find()){
            String str=matcherauthor.group();
            int n=str.lastIndexOf("\">");
            author=str.substring(n+2,str.length()-4);
            book.setAuthor(author);
        }
        //提取出版社
        Pattern patternpress=Pattern.compile("<span class=\"pl\">出版社.*?([^<]*)<br/>");
        Matcher matcherpress=patternpress.matcher(result);
        while (matcherpress.find()){
            String str=matcherpress.group();
            int n=str.lastIndexOf("</spa");
            press=str.substring(n+8,str.length()-5);
            book.setPress(press);
        }
        //提取页数
        Pattern patternpage=Pattern.compile("<span class=\"pl\">页数.*?([^<]*)<br/>");
        Matcher matcherpage=patternpage.matcher(result);
        while (matcherpage.find()){
            String str=matcherpage.group();
            int n=str.lastIndexOf("span>");
            String pa=str.substring(n+6,str.length()-5);
            page=Integer.parseInt(pa);
            book.setPage(page);
        }
        System.out.println(book.toString());
        return book;
    }
}
