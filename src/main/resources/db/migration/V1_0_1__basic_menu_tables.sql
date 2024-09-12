CREATE TABLE categories
(
    id               UUID PRIMARY KEY            DEFAULT gen_random_uuid(),
    name             VARCHAR(50) UNIQUE NOT NULL,
    created_at       TIMESTAMP          NOT NULL DEFAULT NOW(),
    created_by       BIGINT             NOT NULL,
    last_modified_at TIMESTAMP          NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT             NOT NULL
);

CREATE TABLE menu_items
(
    id               UUID PRIMARY KEY        DEFAULT gen_random_uuid(),
    name             VARCHAR(255)   NOT NULL,
    description      TEXT,
    base_price       NUMERIC(10, 2) NOT NULL,
    available        BOOLEAN                 DEFAULT FALSE,
    image_url        VARCHAR(255),
    created_at       TIMESTAMP      NOT NULL DEFAULT NOW(),
    created_by       BIGINT         NOT NULL,
    last_modified_at TIMESTAMP      NOT NULL DEFAULT NOW(),
    last_modified_by BIGINT         NOT NULL,
    category_id      UUID REFERENCES categories (id) ON DELETE CASCADE
);

-- CREATE TABLE item_additions
-- (
--     id               SERIAL PRIMARY KEY,
--     name             VARCHAR(100) NOT NULL,
--     description      TEXT,
--     additional_price NUMERIC(10, 2) DEFAULT 0.00,
--     available        BOOLEAN        DEFAULT FALSE
-- );
--
-- CREATE TABLE menu_items_item_additions
-- (
--     menu_item_id     INT,
--     item_addition_id INT,
--     PRIMARY KEY (menu_item_id, item_addition_id)
-- );

CREATE TABLE coffee_items
(
    item_id        UUID REFERENCES menu_items (id) ON DELETE CASCADE,
    roast_level    VARCHAR(20),
    origin_country VARCHAR(100),
    bean_type      VARCHAR(20)
);

-- orders
-- CREATE TABLE orders
-- (
--     order_id      SERIAL PRIMARY KEY,
--     order_date    TIMESTAMP                                                          DEFAULT CURRENT_TIMESTAMP,
--     total_price   NUMERIC(10, 2) NOT NULL,
--     customer_name VARCHAR(255),
--     status        VARCHAR(20) CHECK (status IN ('Pending', 'Completed', 'Canceled')) DEFAULT 'Pending'
-- );
--
-- CREATE TABLE order_details
-- (
--     order_detail_id SERIAL PRIMARY KEY,
--     order_id        INT REFERENCES orders (order_id) ON DELETE CASCADE,
--     item_id         INT REFERENCES menu_items (item_id),
--     variant_id      INT REFERENCES item_variants (variant_id),
--     quantity        INT            NOT NULL DEFAULT 1,
--     price           NUMERIC(10, 2) NOT NULL
-- );
--
-- CREATE TABLE order_additions
-- (
--     order_addition_id SERIAL PRIMARY KEY,
--     order_detail_id   INT REFERENCES order_details (order_detail_id) ON DELETE CASCADE,
--     addition_id       INT REFERENCES item_additions (addition_id),
--     quantity          INT            NOT NULL DEFAULT 1,
--     price             NUMERIC(10, 2) NOT NULL
-- );
--
-- CREATE TABLE item_reviews
-- (
--     review_id     SERIAL PRIMARY KEY,
--     item_id       INT REFERENCES menu_items (item_id) ON DELETE CASCADE,
--     customer_name VARCHAR(255),
--     rating        INT CHECK (rating BETWEEN 1 AND 5),
--     review_text   TEXT,
--     review_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );
