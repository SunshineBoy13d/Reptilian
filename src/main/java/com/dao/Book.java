package com.dao;

import java.io.Serializable;

public class Book implements Serializable {
    private  int id;
    private  String title;
    private  String author;
    private  String press;
    private  int page;
    public Book(){};
    public Book(int id,String title,String author,String press,int page){
       this.id=id;
       this.title=title;
       this.author = author;
       this.press = press;
       this.page = page;
   }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    @Override
    public String toString(){
        return "id;"+id+"  书名: "+title+"  作者:"+author+"  出版社:"+press+"  页数:"+page;
    }
}
