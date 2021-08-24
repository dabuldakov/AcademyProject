INSERT INTO publisher.city (name)
VALUES ('tomsk');
INSERT INTO publisher.city (name)
VALUES ('novosibirsk');
INSERT INTO publisher."user" (name, password, city_id)
VALUES ('admin', 'admin', 1);
INSERT INTO publisher."user" (name, password, city_id)
VALUES ('user', 'user', 2);
commit;


INSERT INTO publisher."user" (name, password)
VALUES ('admin', 'admin');
INSERT INTO publisher."user" (name, password)
VALUES ('user', 'user');
commit;
