package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class AddBook {
    public void add(Book book)throws SQLException {
        Connection conn=null;
        PreparedStatement preparedStatement=null;
        try {
             conn = DBUtil.getConnection();
            String sql = "insert into book(id,title,author,press,page)values(?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getPress());
            preparedStatement.setInt(5, book.getPage());
            preparedStatement.execute();
        }finally {
            conn.close();
            preparedStatement.close();
        }
    }
}
