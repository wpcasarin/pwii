CREATE TABLE IF NOT EXISTS user
(
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    type          VARCHAR(10) NOT NULL CHECK (type IN ('B2B', 'B2C')),
    email         TEXT        NOT NULL UNIQUE,
    phone         TEXT        NOT NULL,
    business_name TEXT,
    cnpj          TEXT UNIQUE,
    cpf           TEXT UNIQUE,
    name          TEXT,
    created_at    DATETIME    NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    updated_at    DATETIME    NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    deleted_at    DATETIME
);

CREATE TRIGGER prevent_update_on_user_created_at
    BEFORE UPDATE
    ON user
    FOR EACH ROW
BEGIN
    SELECT CASE
               WHEN NEW.created_at != OLD.created_at THEN
                   RAISE(ABORT, 'Cannot update created_at column')
               END;
END;