CREATE DATABASE IF NOT EXISTS dream_facilities CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dream_facilities;

CREATE TABLE IF NOT EXISTS feedback (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  subject VARCHAR(100) NOT NULL,
  message VARCHAR(1500) NOT NULL,
  rating TINYINT NOT NULL CHECK (rating BETWEEN 1 AND 5),
  is_approved BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_feedback_approved_created (is_approved, created_at),
  INDEX idx_feedback_rating (rating)
);

CREATE TABLE IF NOT EXISTS admins (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(80) NOT NULL UNIQUE,
  password_hash CHAR(64) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Demo login: username = admin, password = Admin@123
-- Change this password before using the project beyond a classroom demo.
INSERT INTO admins (username, password_hash)
VALUES ('admin', 'e86f78a8a3caf0b60d8e74e5942aa6d86dc150cd3c03338aef25b7d2d7e3acc7')
ON DUPLICATE KEY UPDATE username = VALUES(username);
