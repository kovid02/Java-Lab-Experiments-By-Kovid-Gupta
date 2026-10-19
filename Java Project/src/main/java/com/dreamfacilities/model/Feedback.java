package com.dreamfacilities.model;
import java.sql.Timestamp;
public class Feedback {
    private int id, rating; private boolean approved; private String name, email, subject, message; private Timestamp createdAt;
    public Feedback(int id, String name, String email, String subject, String message, int rating, Timestamp createdAt, boolean approved) { this.id=id; this.name=name; this.email=email; this.subject=subject; this.message=message; this.rating=rating; this.createdAt=createdAt; this.approved=approved; }
    public int getId(){return id;} public int getRating(){return rating;} public boolean isApproved(){return approved;} public String getName(){return name;} public String getEmail(){return email;} public String getSubject(){return subject;} public String getMessage(){return message;} public Timestamp getCreatedAt(){return createdAt;}
}
