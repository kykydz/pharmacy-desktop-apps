CREATE TABLE customer (
  id CHAR(36) PRIMARY KEY NOT NULL,  -- Use UUID data type
  name VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20),
  email_address VARCHAR(255),
  description VARCHAR(255),
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stock (
  id CHAR(36) PRIMARY KEY NOT NULL,  -- Use UUID data type
  medication_name VARCHAR(255) NOT NULL,
  description TEXT,
  price INTEGER NOT NULL,
  quantity_available INT NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP default CURRENT_TIMESTAMP
);



CREATE TABLE transaction (
  id CHAR(36) PRIMARY KEY NOT null,  -- Use UUID data type
  customer_id CHAR(36) NOT NULL,
  total_price DECIMAL(10,2) NOT NULL,
  meta_data JSON not null,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(id)
);


create table transaction_item (
	id CHAR(36) PRIMARY KEY NOT null,  -- Use UUID data type
	transaction_id CHAR(36) not null,
	stock_id CHAR(36) not null,
	quantity INT NOT null,
	FOREIGN KEY (stock_id) REFERENCES stock(id),
	FOREIGN KEY (transaction_id) REFERENCES transaction(id)
)

