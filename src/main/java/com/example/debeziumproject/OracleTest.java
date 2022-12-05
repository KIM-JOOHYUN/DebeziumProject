package com.example.debeziumproject;

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
        try (Connection con =
                     DriverManager.getConnection(
                             "jdbc:oracle:thin:@orademoapt?TNS_ADMIN=C:/Users/infen/OneDrive/Desktop/SW22_카카오엔터프라이즈/데이터 관리 기술/Oracle/Wallet_orademoapt",
                             "admin",
                             "KTgLi-$sgZbj36n"
                     )) {
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
