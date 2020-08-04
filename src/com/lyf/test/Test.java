package com.lyf.test;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = null;
        Scanner sc = new Scanner(System.in);
        Statement statement = null;
        boolean isLogin = false;
        while (true) {
            if (!isLogin) {
                System.out.println("请输入 tns连接串:");
                String tnsSql = sc.nextLine();
                System.out.println("请输入用户名:");
                String userName = sc.nextLine();
                System.out.println("请输入密码:");
                String passwd = sc.nextLine();
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    System.out.println("开始尝试连接...");
                    connection = DriverManager.getConnection(tnsSql, userName, passwd);
                    System.out.println("登录成功!");
                    isLogin = true;
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("请输入sql类型:");
                System.out.println("DDL -->1");
                System.out.println("查询语句 -->2");
                String type = sc.nextLine();

                if ("1".equals(type.trim())) {
                    System.out.println("请输入DDL语句:");
                    String input = sc.nextLine();
                    /*try {
                        statement = connection.createStatement();
                        statement.execute(input);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        statement.close();
                    }*/
                    try (Statement statement1 = connection.createStatement()) {
                        statement1.execute(input);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                } else if ("2".equals(type.trim())) {
                    System.out.println("请输入DML语句:");
                    String input = sc.nextLine();
                    try {
                        statement = connection.prepareStatement(input);
                        ResultSet resultSet = statement.executeQuery(input);
                        ResultSetMetaData var6 = resultSet.getMetaData();

                        int var7;
                        for (var7 = 0; var7 < var6.getColumnCount(); ++var7) {
                            System.out.format("%28s", var6.getColumnName(var7 + 1));
                        }

                        System.out.print("\n");

                        while (resultSet.next()) {
                            for (var7 = 0; var7 < var6.getColumnCount(); ++var7) {
                                System.out.format("%28s", resultSet.getString(var7 + 1));
                            }

                            System.out.print("\n");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        statement.close();
                    }
                }


            }

        }
    }
}
