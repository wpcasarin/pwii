CREATE TABLE IF NOT EXISTS sale
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id    INTEGER  NOT NULL,
    created_at DATETIME NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    updated_at DATETIME NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    deleted_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TRIGGER prevent_update_on_sale_created_at
    BEFORE UPDATE
    ON sale
    FOR EACH ROW
BEGIN
    SELECT CASE
               WHEN NEW.created_at != OLD.created_at THEN
                   RAISE(ABORT, 'Cannot update created_at column')
               END;
END;
