package com.dreamfacilities.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil {
    private DBUtil() {}
    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("DREAM_FACILITIES_DB_URL", "jdbc:mysql://localhost:3306/dream_facilities?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true");
        String user = System.getenv().getOrDefault("DREAM_FACILITIES_DB_USER", "root");
        String password = System.getenv().getOrDefault("DREAM_FACILITIES_DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }
}
