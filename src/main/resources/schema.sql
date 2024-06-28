CREATE TABLE IF NOT EXISTS todo (
    todo_id VARCHAR(255) PRIMARY KEY,
    todo_title VARCHAR(255) NOT NULL,
    finished BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);
