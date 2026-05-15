INSERT INTO app_user (username,age) VALUES ('dude',26);
INSERT INTO app_user (username,age) VALUES ('duke',18);
INSERT INTO app_user (username,age) VALUES ('jack',16);

SELECT COUNT(*) AS total
FROM app_user;

SELECT id, username, age
FROM app_user
ORDER BY age DESC;