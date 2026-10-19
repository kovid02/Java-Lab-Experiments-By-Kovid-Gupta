package com.dreamfacilities.dao;
import com.dreamfacilities.model.Feedback;
import com.dreamfacilities.util.DBUtil;
import java.sql.*; import java.util.*;
public class FeedbackDAO {
    public void add(String name,String email,String subject,String message,int rating) throws SQLException {
        String sql="INSERT INTO feedback(name,email,subject,message,rating) VALUES(?,?,?,?,?)";
        try(Connection c=DBUtil.getConnection(); PreparedStatement p=c.prepareStatement(sql)){p.setString(1,name);p.setString(2,email);p.setString(3,subject);p.setString(4,message);p.setInt(5,rating);p.executeUpdate();}
    }
    public List<Feedback> find(String search,int rating,boolean all) throws SQLException {
        List<Feedback> out=new ArrayList<>(); StringBuilder sql=new StringBuilder("SELECT id,name,email,subject,message,rating,created_at,is_approved FROM feedback WHERE 1=1");
        if(!all) sql.append(" AND is_approved=1"); if(search!=null&&!search.isBlank())sql.append(" AND (name LIKE ? OR subject LIKE ? OR message LIKE ?)"); if(rating>0)sql.append(" AND rating=?"); sql.append(" ORDER BY created_at DESC");
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql.toString())){int i=1;if(search!=null&&!search.isBlank()){String q="%"+search.trim()+"%";p.setString(i++,q);p.setString(i++,q);p.setString(i++,q);}if(rating>0)p.setInt(i,rating);try(ResultSet r=p.executeQuery()){while(r.next())out.add(new Feedback(r.getInt("id"),r.getString("name"),r.getString("email"),r.getString("subject"),r.getString("message"),r.getInt("rating"),r.getTimestamp("created_at"),r.getBoolean("is_approved")));}}return out;
    }
    public int count() throws SQLException {try(Connection c=DBUtil.getConnection();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COUNT(*) FROM feedback WHERE is_approved=1")){r.next();return r.getInt(1);}}
    public double average() throws SQLException {try(Connection c=DBUtil.getConnection();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COALESCE(AVG(rating),0) FROM feedback WHERE is_approved=1")){r.next();return r.getDouble(1);}}
    public int approve(int id,boolean approved) throws SQLException {try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("UPDATE feedback SET is_approved=? WHERE id=?")){p.setBoolean(1,approved);p.setInt(2,id);return p.executeUpdate();}}
    public int delete(int id) throws SQLException {try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM feedback WHERE id=?")){p.setInt(1,id);return p.executeUpdate();}}
}
