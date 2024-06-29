CREATE TABLE IF NOT EXISTS todo (
    todo_id VARCHAR(36) PRIMARY KEY,
    todo_title VARCHAR(100) NOT NULL,
    finished BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(124) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL
)
