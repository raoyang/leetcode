package org.sunshine.lc.test.mysql;

import java.sql.*;

public class Test {

    private volatile boolean con = false;
    private static String sql0 = "DROP TABLE IF EXISTS I_smfn0ayr7j7705baee";
    private static String sql1 = "CREATE TABLE I_smfn0ayr7j7705baee (ObjectId CHAR (37) PRIMARY KEY, `ParentObjectId` CHAR(37), `ParentPropertyName` VarChar(100), `ParentIndex` INT, `FEA4F027` VarChar(200), `Name` VarChar(200), `CreatedBy` CHAR(37), `OwnerId` CHAR(37), `OwnerDeptId` JSON, `CreatedTime` DateTime, `ModifiedBy` CHAR(37), `ModifiedTime` DateTime, `Status` INT, `Version` BIGINT, `FF41B8ED` VarChar(200), `F5D70E22` VarChar(200), `F7D350E7` VarChar(200), `F6A336D4` VarChar(200), `F035824A` Decimal(23, 8), `F7E92E1F` Text(2000), `FF70D826` VarChar(200), `FC5D3ED7` Decimal(23, 8), `F4B55C1D` VarChar(200), `FB994C86` VarChar(200))";
    private static String sql2 = "CREATE  INDEX I_rio699cn7webl7uzjlcnj3e62 ON I_smfn0ayr7j7705baee (ParentObjectId)";
    private static String sql3 = "CREATE  INDEX I_p8xrogce38qes7bagffi30mz1 ON I_smfn0ayr7j7705baee (CreatedBy)";
    private static String sql4 = "CREATE  INDEX I_mj1nbpgxljw1f9hwjatjqvh75 ON I_smfn0ayr7j7705baee (OwnerId)";
    private static String sql5 = "CREATE  INDEX I_yicld23rc2rrfinljf0ilcah3 ON I_smfn0ayr7j7705baee (ModifiedBy)";
    private static String sql6 = "DELETE FROM I_smfn0ayr7j7705baee";

    public static void main(String args[]) throws SQLException, ClassNotFoundException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection1 =  DriverManager.getConnection("jdbc:mysql://localhost:3306/join?allowMultiQueries=true&autoReconnect=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "123456");
        connection1.setAutoCommit(false);
        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/join?allowMultiQueries=true&autoReconnect=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "123456");
        connection2.setAutoCommit(false);

        final Test t = new Test();
        //t.test4(connection1);
        Thread t1 = new Thread(()->{
            t.test3(connection1);
        });
        Thread t2 = new Thread(()->{
            t.test4(connection2);
        });

        t1.start();
        t2.start();
        Thread.sleep(200000);
    }

    private void test3(Connection connection){
        try {
            Statement statement = connection.createStatement();
            System.out.println("a");
            statement.execute(sql0);
            statement.execute(sql1);
            statement.execute(sql2);
            statement.execute(sql3);
            statement.execute(sql4);
            statement.execute(sql5);
            statement.execute(sql6);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void test4(Connection connection){
        try {
            Statement statement = connection.createStatement();
            String sql = "SET TRANSACTION ISOLATION LEVEL READ COMMITTED";
            statement.execute(sql);
            statement.execute("begin");
            /*
            Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/join?allowMultiQueries=true&autoReconnect=true&characterEncoding=utf-8&serverTimezone=UTC", "root", "123456");
            connection2.setAutoCommit(false);
            Statement st1 = connection2.createStatement();
            st1.execute(sql0);
            st1.execute(sql1);
            st1.execute(sql2);
            st1.execute(sql3);
            st1.execute(sql4);
            st1.execute(sql5);
            System.out.println("完毕");*/
            while(!con){
                System.out.println("wait");
            }
            Thread.sleep(5);
            statement.execute(sql6);
            System.out.println("over");
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void alterTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sql = "alter table tb_course_1 add index index_course(course_name) using btree";
            Thread.sleep(5000);
            con = true;
            System.out.println("我要去修改表结构了哈!");
            statement.execute(sql);
            System.out.println("修改表结构完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void test(Connection connection)  {
        try {
            Statement statement = connection.createStatement();
            String sql = "SET TRANSACTION ISOLATION LEVEL REPEATABLE READ";
            statement.execute(sql);
            statement.execute("begin");
            ResultSet rs = statement.executeQuery("select * from tb_course_1 where id = 156167");
            while(rs.next()){
                int id = rs.getInt("id");
                String courseName = rs.getString("course_name");
                System.out.println(id);
                System.out.println(courseName);
            }
            System.out.println("开始执行删除");
            con = true;
            statement.execute("delete from tb_course_1");
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void test1(Connection connection)  {
        try {
            Statement statement = connection.createStatement();
            String sql = "SET TRANSACTION ISOLATION LEVEL REPEATABLE READ";
            statement.execute(sql);
            statement.execute("begin");
            ResultSet rs = statement.executeQuery("select * from tb_course_1 where id = 156167");
            while(rs.next()){
                int id = rs.getInt("id");
                String courseName = rs.getString("course_name");
                System.out.println(id);
                System.out.println(courseName);
            }
            while(!con){
                Thread.sleep(2);
            }
            Thread.sleep(15);
            System.out.println("我也要删除");
            statement.execute("delete from tb_course_1");
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
