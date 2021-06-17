-- Blacklist
insert into blacklist (password) values ("123456");
insert into blacklist (password) values ("123456789");
insert into blacklist (password) values ("picture1");
insert into blacklist (password) values ("password");
insert into blacklist (password) values ("12345678");
insert into blacklist (password) values ("111111");
insert into blacklist (password) values ("123123");
insert into blacklist (password) values ("12345");
insert into blacklist (password) values ("1234567890");
insert into blacklist (password) values ("senha");
insert into blacklist (password) values ("1234567");
insert into blacklist (password) values ("qwerty");
insert into blacklist (password) values ("abc123");
insert into blacklist (password) values ("Million2");
insert into blacklist (password) values ("000000");
insert into blacklist (password) values ("1234");
insert into blacklist (password) values ("iloveyou");
insert into blacklist (password) values ("aaron431");
insert into blacklist (password) values ("password1");
insert into blacklist (password) values ("qqww1122");

-- Roles
insert into roles (id, name) values (1, "ROLE_USER");
insert into roles (id, name) values (2, "ROLE_ADMIN");
insert into roles (id, name) values (3, "ROLE_AGENT");
--insert into roles (id, name) values (4, "ROLE_GUEST");

-- Permissions
--insert into permission (id, name) values (1, "ALL_TAGS");
insert into permission (id, name) values (10, "ALL_USERS");
insert into permission (id, name) values (11, "GET_USER");
insert into permission (id, name) values (12, "CHANGE_PASS");
insert into permission (id, name) values (13, "CHANGE_USERNAME");



--insert into roles_permission (role_id, permission_id) values (4, 1);
insert into roles_permission (role_id, permission_id) values (1, 11);
insert into roles_permission (role_id, permission_id) values (2, 10);
insert into roles_permission (role_id, permission_id) values (1, 12);
insert into roles_permission (role_id, permission_id) values (1, 13);


-- User Credentials : ADMIN
insert into user_credentials (id, username, password, verified) values (500, 'lubura', '$2a$10$s3iq1XL.6BS4ckuXO5flkOvwghjLs/ImwrYpY9RRuJXDFs9Z8qjV.', true);
insert into user_role (user_id, role_id) values (500, 2);
-- pass: Lubura123*

-- USER
insert into user_credentials (id, username, password, verified) values (550, 'maja', '$2a$10$awcKLtXVr3yW6eTjoYfSY.0Dnnpgz0WkjeY3Q63TuNa2z3xdeYJo6', true);
insert into user_role (user_id, role_id) values (550, 1);
-- pass: Maja123*
insert into user_credentials (id, username, password, verified) values (551, 'tasa', '$2a$10$NIxgOguWYESwXmVYSEx/K.10C2UyuN..u/CL9ObBC/iQaOGZZ8FtO', true);
insert into user_role (user_id, role_id) values (551, 1);
-- pass: Tasa123*
insert into user_credentials (id, username, password, verified) values (552, 'masa', '$2a$10$jIXJCEzeey1J7ju2EJF24e/Bptz0vAsPB8AmgBB6spPTfjhNLqRpW', true);
insert into user_role (user_id, role_id) values (552, 1);
-- pass: Masa123*
insert into user_credentials (id, username, password, verified) values (553, 'gojko', '$2a$10$aJHEn4Tr8f.ztJ3OnKnZ3endkbaN1ioLZxPmziVy/JIK/Sraaenxa', true);
insert into user_role (user_id, role_id) values (553, 1);
-- pass: Gojko123*
-- AGENT
insert into user_credentials (id, username, password, verified) values (700, 'nike', '$2a$10$OD1KiQdb8j7b1yPuzC3NQO8TnhCKaxe1zWc3.QgHBEH0y7hN6G7Ia', true);
insert into user_role (user_id, role_id) values (700, 3);
-- pass : Nike123*
insert into user_credentials (id, username, password, verified) values (701, 'samsung', '$2a$10$jDvsku0joAHAfQk9i2Le4.AoW2jsVb5Zcsyx8ieR7hAbuTNl9MsGe', true);
insert into user_role (user_id, role_id) values (701, 3);
-- pass: Samsung123*