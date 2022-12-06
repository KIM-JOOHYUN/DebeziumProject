package com.example.debeziumproject;

import oracle.jdbc.driver.OracleConnection;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.aspectj.bridge.MessageUtil.fail;

public class OracleTest {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (OracleConnection con =
                     (OracleConnection)DriverManager.getConnection(
                             "jdbc:oracle:thin:@orademoapt_medium?TNS_ADMIN=/Users/infen/Documents/Wallet_orademoapt/",
                             "admin",
                             "KTgLi-$sgZbj36n"
                     )
        ) {
            System.out.println("Conncetion : " + con);
        } catch (Exception e) {
            System.out.println(e);
            fail(e.getMessage());
        }
    }
}
