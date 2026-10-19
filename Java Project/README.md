# Dream Facilities - Student Feedback & Management System

A small Advanced Java web application built with JSP, Servlets, JDBC and MySQL. It includes a responsive glassmorphism interface, blue/white/navy/grey/light-yellow palette, light/dark mode, smooth hover and entrance animations, star ratings, public search and rating filters, and a session-protected admin dashboard.

## Requirements
- JDK 17 or newer
- Apache Maven 3.8+
- Apache Tomcat 10.1.x (Jakarta Servlet 6)
- MySQL 8.x
- Internet connection for the optional Google Fonts import; the app uses system fallbacks if unavailable

## 1. Create the database
1. Start MySQL.
2. Open MySQL Workbench or a MySQL terminal.
3. Run `database/dream_facilities.sql`.
4. The script creates the `dream_facilities` database, tables, and demo admin account.

Demo administrator:
- Username: `admin`
- Password: `Admin@123`

The demo password is stored as a SHA-256 hash. This is suitable only for a classroom demonstration; use a modern salted password hash (for example BCrypt/Argon2) for a real deployment and change the demo password.

## 2. Configure database access
Defaults are:
- URL: `jdbc:mysql://localhost:3306/dream_facilities?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true`
- Username: `root`
- Password: blank

If your MySQL settings differ, set these environment variables before starting Tomcat:
- `DREAM_FACILITIES_DB_URL`
- `DREAM_FACILITIES_DB_USER`
- `DREAM_FACILITIES_DB_PASSWORD`

Alternatively, edit the defaults in `src/main/java/com/dream_facilities/util/DBUtil.java`.

## 3. Build the WAR
Open a terminal in the project folder and run:

```bash
mvn clean package
```

The output will be `target/DreamFacilities.war`.

## 4. Deploy to Tomcat 10.1 on Windows
1. Stop Tomcat if it is running.
2. Copy `target\DreamFacilities.war` into your Tomcat `webapps` folder.
3. Start Tomcat using `bin\startup.bat` (or start the Tomcat service).
4. Open `http://localhost:8080/DreamFacilities/` in your browser.
5. Admin dashboard: `http://localhost:8080/DreamFacilities/admin/login`.

If the app is already deployed, stop Tomcat and remove the previous `webapps\DreamFacilities` folder and `webapps\DreamFacilities.war` before copying the new WAR.

## Features
- Home page with feedback count and average rating
- Submit feedback with required fields, subject category and interactive 1-5 star rating
- Public feedback page with keyword search and rating filter; only approved feedback is public
- Admin login with session-based access control
- Admin can approve, hide, or delete feedback
- Responsive layout, glass panels, hover lifts, animated buttons, card entrances, stat count-up, theme toggle and reduced-motion support
- Light/dark mode preference saved in the browser

## Project structure

```text
DreamFacilities/
├── database/dream_facilities.sql
├── src/main/java/com/dream_facilities/
│   ├── dao/                 # JDBC queries
│   ├── model/               # Feedback model
│   ├── servlet/             # HTTP routes and session checks
│   └── util/                # Database and password helpers
├── src/main/webapp/
│   ├── assets/css/style.css
│   ├── assets/js/app.js
│   └── WEB-INF/views/       # JSP pages and shared fragments
├── pom.xml
└── README.md
```

## Troubleshooting
- **HTTP 404:** Check that the URL includes `/DreamFacilities/` and Tomcat has deployed the WAR.
- **Database connection error:** Check that MySQL is running, import the SQL script, and confirm your DB environment variables.
- **JSP/Servlet class errors:** Use Tomcat 10.1, not Tomcat 9. Tomcat 10 uses the `jakarta.*` packages.
- **Maven not recognized:** Install Maven and add its `bin` folder to Windows PATH, or use your IDE's Maven panel.
- **Port 8080 busy:** Change Tomcat's HTTP connector port in `conf/server.xml`.

## Viva quick notes
1. **What is JSP?** JavaServer Pages lets us create dynamic web pages using Java-backed server-side templates.
2. **What is a Servlet?** A Java class that handles HTTP requests and responses.
3. **What is JDBC?** Java Database Connectivity, used to execute SQL from Java.
4. **Why use PreparedStatement?** It parameterizes values and helps prevent SQL injection.
5. **What is a session?** Server-side state associated with a browser across requests; this app uses it to protect the admin dashboard.
6. **What is MVC?** A design pattern separating data/model, request handling/controller, and presentation/view.
7. **Why is feedback approval used?** Submitted feedback stays hidden from public users until an admin approves it.
8. **How does dark mode work?** JavaScript switches a `data-theme` attribute and CSS variables update the interface colours.
9. **What is a WAR file?** A Web Application Archive used to deploy a Java web application to a servlet container.
10. **Why Tomcat 10.1?** The code uses Jakarta Servlet 6 and `jakarta.servlet.*` imports.
