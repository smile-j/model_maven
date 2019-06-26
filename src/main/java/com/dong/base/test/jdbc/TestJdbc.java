package com.dong.base.test.jdbc;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2017/7/31.
 */
public class TestJdbc {



    @Test
    public void testFunction(){
        try {//C:\dong\test_project\bbs\src\com\bbs\common\test\jdbc\TestJdbc.java
            InputStream inputStream = getClass().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String url= (String) properties.get("jdbcUrl");
            String driverName = (String) properties.get("driver");
            String user = (String) properties.get("user");
            String password = (String) properties.get("pass");

            Connection con = DriverManager.getConnection(url,user,password);
            CallableStatement callableStatement =  con.prepareCall("{? = call testfunction(?)}");
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(2,2);
            callableStatement.execute();
//            ResultSet resultSet = callableStatement.executeQuery();
//            ResultSetMetaData rsm = resultSet.getMetaData();
//            while (resultSet.next()){
//                Integer id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String remarks= resultSet.getString("remarks");
//                System.out.println(id+"  "+name+" "+remarks);
//            }

//            resultSet.close();
            System.out.println("-----------"+callableStatement.getString(1));
            callableStatement.close();
            con.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testProcedure(){
        try {//C:\dong\test_project\bbs\src\com\bbs\common\test\jdbc\TestJdbc.java
            InputStream inputStream = getClass().getResourceAsStream("jdbc.properties");
//            File template = new File(request.getSession().getServletContext().getRealPath("/")+"/WEB-INF/template/billList.xls");
//            InputStream inputStream =this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            String url= (String) properties.get("jdbcUrl");
            String driverName = (String) properties.get("driver");
            String user = (String) properties.get("user");
            String password = (String) properties.get("pass");

            Connection con = DriverManager.getConnection(url,user,password);
            CallableStatement callableStatement =  con.prepareCall("{ call testProcedure(?)}");
//            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(1,5);
//            callableStatement.execute();
            ResultSet resultSet = callableStatement.executeQuery();
            ResultSetMetaData rsm = resultSet.getMetaData();
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String remarks= resultSet.getString("remarks");
                System.out.println(id+"  "+name+" "+remarks);
            }

//            resultSet.close();
//            System.out.println("-----------"+callableStatement.getString(1));
            callableStatement.close();
            con.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
