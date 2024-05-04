
CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    fullname VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    user_password VARCHAR(100) NOT NULL DEFAULT '', -- encoded
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

ALTER TABLE users ADD COLUMN role_id INT;

ALTER TABLE users ADD CONSTRAINT fk_users_role_id FOREIGN KEY (role_id) REFERENCES roles(id);

CREATE TABLE tokens(
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_date TIMESTAMP,
    revoked BOOLEAN NOT NULL,
    expired BOOLEAN NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE social_accounts(
    id SERIAL PRIMARY KEY,
    provider VARCHAR(20) NOT NULL,
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL,
    name VARCHAR(100) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

COMMENT ON COLUMN social_accounts.provider IS 'The main of name social network';
COMMENT ON COLUMN social_accounts.email IS 'Email Account';
COMMENT ON COLUMN social_accounts.name IS 'Name Account';

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL DEFAULT ''
);

COMMENT ON COLUMN categories.name IS 'Name of category, example: food, toys,..';

CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    name VARCHAR(350),
    price FLOAT NOT NULL CHECK(price>=0),
    thumbnail VARCHAR(300) DEFAULT '',
    description TEXT DEFAULT '',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE product_images(
    id SERIAL PRIMARY KEY,
    product_id INT,
    image_url VARCHAR(300),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE orders(
    id SERIAL PRIMARY KEY,
    user_id INT,
    fullname VARCHAR(100) DEFAULT '',
    email VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    note VARCHAR(100) DEFAULT '',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20),
    total_money FLOAT CHECK(total_money>=0),
    shipping_method VARCHAR(100),
    shipping_address VARCHAR(200),
    shipping_date DATE,
    tracking_number VARCHAR(100),
    payment_method VARCHAR(100),
    active BOOLEAN
);

ALTER TABLE orders ALTER COLUMN status TYPE VARCHAR(20) USING status::VARCHAR;

CREATE TABLE order_details(
    id SERIAL PRIMARY KEY,
    order_id INT,
    product_id INT,
    price FLOAT CHECK(price>=0),
    number_of_products INT CHECK(number_of_products>0),
    total_money FLOAT CHECK(total_money>=0),
    color VARCHAR(20) DEFAULT ''
);

CREATE TABLE coupons (
    id SERIAL PRIMARY KEY,
    code VARCHAR(255),
    discount_value FLOAT,
    discount_type VARCHAR(255),
    expiration_date DATE
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_content TEXT,
    rating INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN,
    order_id INT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);