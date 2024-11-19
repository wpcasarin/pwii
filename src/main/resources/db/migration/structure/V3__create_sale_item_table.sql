CREATE TABLE IF NOT EXISTS sale_item
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    amount     REAL     NOT NULL,
    product_id INTEGER  NOT NULL,
    sale_id    INTEGER  NOT NULL,
    created_at DATETIME NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    updated_at DATETIME NOT NULL DEFAULT (STRFTIME('%Y-%m-%d %H:%M:%f', 'NOW')),
    deleted_at DATETIME,
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (sale_id) REFERENCES sale (id)
);

CREATE TRIGGER prevent_update_on_sale_item_created_at
    BEFORE UPDATE
    ON sale_item
    FOR EACH ROW
BEGIN
    SELECT CASE
               WHEN NEW.created_at != OLD.created_at THEN
                   RAISE(ABORT, 'Cannot update created_at column')
               END;
END;
