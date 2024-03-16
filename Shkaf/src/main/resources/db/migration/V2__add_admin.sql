INSERT INTO users (id, name, is_archived, email, password, role, bucket_id)
VALUES (1, 'admin', false, 'bahankova.dasha@gmail.com', 'pass', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;