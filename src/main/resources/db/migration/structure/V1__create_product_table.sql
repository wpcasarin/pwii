CREATE TABLE IF NOT EXISTS product
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT NOT NULL,
    price       REAL NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    updated_at  DATETIME NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    deleted_at  DATETIME
);

CREATE TRIGGER prevent_update_on_product_created_at
    BEFORE UPDATE
    ON product
    FOR EACH ROW
BEGIN
    SELECT CASE
               WHEN NEW.created_at != OLD.created_at THEN
                   RAISE(ABORT, 'Cannot update created_at column')
               END;
END;



