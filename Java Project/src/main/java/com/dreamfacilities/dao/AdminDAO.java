package com.dreamfacilities.dao;
import com.dreamfacilities.util.DBUtil; import com.dreamfacilities.util.PasswordUtil; import java.sql.*;
public class AdminDAO { public boolean authenticate(String username,String password) throws SQLException {try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("SELECT password_hash FROM admins WHERE username=?")){p.setString(1,username);try(ResultSet r=p.executeQuery()){return r.next() && PasswordUtil.sha256(password).equalsIgnoreCase(r.getString(1));}}} }
