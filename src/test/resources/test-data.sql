INSERT INTO categories (id, name, created_by, last_modified_by)
VALUES ('3c91cc57-298a-4e88-9b40-a52d4720bb93', 'coffee', 1, 1),
       ('3c91cc57-298a-4e88-9b40-a52d4720bb92', 'tea', 1, 1);

INSERT INTO menu_items (id, name, description, base_price, image_url, created_by, last_modified_by, category_id)
VALUES ('3c91cc57-298a-4e88-9b40-a52d4720bb93', 'ruff', 'test', 10.00, 'url', 1, 1,
        (SELECT id FROM categories WHERE categories.name = 'coffee')),
       ('3c91cc57-298a-4e88-9b40-a52d4720bb92', 'black tea', 'test', 10.00, 'url', 1, 1,
        (SELECT id FROM categories WHERE categories.name = 'tea'));
