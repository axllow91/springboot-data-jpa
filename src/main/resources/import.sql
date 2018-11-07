/* Populate tables */
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24', '');
INSERT INTO clients (first_name, last_name, email, create_at, image) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25', '');


INSERT INTO products (product_name, price, created_at) VALUES('Panasonic LCD', 255.30, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Samsungi LED', 300.40, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Sony Camera DSC-W320B', 432.50, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Apple Iphone X', 1000, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Apple iPod Shuffle', 55.30, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Sony NoteBook Z110', 1230.44, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Monster Bicycle', 355.30, NOW());
INSERT INTO products (product_name, price, created_at) VALUES('Benq XL 144 HZ', 225.10, NOW());

INSERT INTO bills (description, observation, client_id, created_at) VALUES('invoice office equipment', null, 1, NOW());
INSERT INTO item_bill(amount, product_id, bill_id) VALUES (1,1,1);
INSERT INTO item_bill(amount, product_id, bill_id) VALUES (4,4,1);
INSERT INTO item_bill(amount, product_id, bill_id) VALUES (5,5,1);
INSERT INTO item_bill(amount, product_id, bill_id) VALUES (6,7,1);

INSERT INTO bills(description, observation, client_id, created_at) VALUES('Microphone Bill', 'some important note', 1, NOW());
INSERT INTO item_bill(amount, product_id, bill_id) VALUES(3,6,1);