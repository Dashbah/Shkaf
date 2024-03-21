INSERT INTO users (id, name, is_archived, email, password, role)
VALUES (1, 'admin', false, 'bahankova.dasha@gmail.com', '$2a$10$uVOo88bczuynq.IXmwrVmeecQlO4iyWPyTpZqywCcXEIvBS/UH42q', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;