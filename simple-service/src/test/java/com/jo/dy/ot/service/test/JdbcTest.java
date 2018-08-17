package com.jo.dy.ot.service.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class JdbcTest {

	public static void main(String[] args) throws Exception {
		  //注册驱动，反射方式加载
        Class.forName("com.mysql.jdbc.Driver");
        //设置url
        String url = "jdbc:mysql://peer1:3306/simple";//person是数据库名，连接是数据库须要开启
        //设置用户名
        String username = "root";
        //设置密码
        String password = "root";
        //获得连接对象
        Connection con = DriverManager.getConnection(url, username, password);
        System.out.println(con);
        Statement statement = con.createStatement();
//        //获得执行者对象
//        String sql = "select * from phones";
//        PreparedStatement ps = con.prepareStatement(sql);
//        //获得结果集
//        ResultSet rs = ps.executeQuery();
//        //结果集处理，
//        while(rs.next()){
//            System.out.println(rs.getString("id")+"  "+rs.getString("pinpai")+"  "+rs.getString("xinghao")+"  "+rs.getString("jiage"));
//        }
        //释放资源
//        rs.close();
//        ps.close();
        con.close();
		
	}
	
}
