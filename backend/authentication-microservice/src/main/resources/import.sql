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
insert into user_credentials (id, username, password, verified) values (101, 'maja', '$2a$10$awcKLtXVr3yW6eTjoYfSY.0Dnnpgz0WkjeY3Q63TuNa2z3xdeYJo6', true);
insert into user_role (user_id, role_id) values (101, 1);
-- pass: Maja123*
insert into user_credentials (id, username, password, verified) values (102, 'tasa', '$2a$10$NIxgOguWYESwXmVYSEx/K.10C2UyuN..u/CL9ObBC/iQaOGZZ8FtO', true);
insert into user_role (user_id, role_id) values (102, 1);
-- pass: Tasa123*
insert into user_credentials (id, username, password, verified) values (103, 'masa', '$2a$10$jIXJCEzeey1J7ju2EJF24e/Bptz0vAsPB8AmgBB6spPTfjhNLqRpW', true);
insert into user_role (user_id, role_id) values (103, 1);
-- pass: Masa123*
insert into user_credentials (id, username, password, verified) values (104, 'gojko', '$2a$10$aJHEn4Tr8f.ztJ3OnKnZ3endkbaN1ioLZxPmziVy/JIK/Sraaenxa', true);
insert into user_role (user_id, role_id) values (104, 1);
-- pass: Gojko123*
insert into user_credentials (id, username, password, verified) values (105, 'mark', '$2a$10$1z4R05P2kpXqFzW5KvW.oug66CI8yfEVwVUcXQDU1WCfWXlL6veLG', true);
insert into user_role (user_id, role_id) values (105, 1);
-- pass: Mark123*
insert into user_credentials (id, username, password, verified) values (106, 'mare', '$2a$10$M6Yz7AQfb3v0g4Pkwcg2WemToQYBlCiTuDZmay1EXLKsMw8/Xk1Xy', true);
insert into user_role (user_id, role_id) values (106, 1);
-- pass: Mare123*
insert into user_credentials (id, username, password, verified) values (107, 'milica', '$2a$10$pNducfmIEE8Sp1k7les1qeVSa7CFbFuTyzFPXWNI9gI3na7JnNE8S', true);
insert into user_role (user_id, role_id) values (107, 1);
-- pass: Milica123*
insert into user_credentials (id, username, password, verified) values (108, 'jelena', '$2a$10$8n4pO3OecZG8QtdmKjzTPO9FarvzsBsHrU0oZTVEDWyAJ85RJw4rq', true);
insert into user_role (user_id, role_id) values (108, 1);
-- pass: Jelena123*
insert into user_credentials (id, username, password, verified) values (109, 'jovana', '$2a$10$4LJWNxiwqRRxjDEhSxLxD.LeGex6E2HBIdfNKGk7ABSGMo/SYKqyq', true);
insert into user_role (user_id, role_id) values (109, 1);
-- pass: Jovana123*
insert into user_credentials (id, username, password, verified) values (110, 'milos', '$2a$10$Ke5NugaEQtDvOBJEOm/7LuUTZP6vrX8SFeC2ux8ios6ZLh7rUcwvm', true);
insert into user_role (user_id, role_id) values (110, 1);
-- pass: Milos123*

insert into user_credentials (id, username, password, verified) values (201, 'jason', '$2a$10$c8EgYxp1eDYPJgmm20vWrue/iGCiJajVNhEz34Jg4Ggr2dWVOK1LK', true);
insert into user_role (user_id, role_id) values (201, 1);
-- pass: Jason123*
insert into user_credentials (id, username, password, verified) values (202, 'josh', '$2a$10$HJh9EAchtuXhmxQL8vpwQOn0xWIhGj0hylirKUKAKjgNear2fuVXC', true);
insert into user_role (user_id, role_id) values (202, 1);
-- pass: Josh123*
insert into user_credentials (id, username, password, verified) values (203, 'therock', '$2a$10$Rq6RrkLrR5nhYxNcBHQDSOZKAeMs/Jj.q2ZkYiWNubFnQmyoOTwd6', true);
insert into user_role (user_id, role_id) values (203, 1);
-- pass: Throck123*
insert into user_credentials (id, username, password, verified) values (204, 'margarot', '$2a$10$IaNltS8KYpUn6HfguyApVuXiyvQAYUmrX3Y1oKVGB9OYW9ZkGeX/m', true);
insert into user_role (user_id, role_id) values (204, 1);
-- pass: Margarot123*
insert into user_credentials (id, username, password, verified) values (205, 'galg', '$2a$10$tR6z1zFujDgjKkKqBqoM9e5gNNCI5tXJ5DOoU.vtErEyfPzalFZv2', true);
insert into user_role (user_id, role_id) values (205, 1);
-- pass: Galg123*
insert into user_credentials (id, username, password, verified) values (206, 'tomcruise', '$2a$10$CEHZV3c/KYetMFCfb1s0quite42PgTM17pXqFPDFjKLKNTCI3B77S', true);
insert into user_role (user_id, role_id) values (206, 1);
-- pass: Tomcruise123*
insert into user_credentials (id, username, password, verified) values (207, 'tomholland', '$2a$10$mS/VFIFXMmOZWu5fqs0DgeI8tu4univ2cnILIDaK1OZ.krePVBbZS', true);
insert into user_role (user_id, role_id) values (207, 1);
-- pass: Tomholland123*
insert into user_credentials (id, username, password, verified) values (208, 'johncena', '$2a$10$MJDoSfQ0MdWZh1i34P65Q.6k7N1lorqT8B3JwcbDGuYc8nNeCRCcK', true);
insert into user_role (user_id, role_id) values (208, 1);
-- pass: Johncena123*

insert into user_credentials (id, username, password, verified) values (250, 'nsuzivo.rs', '$2a$10$FE.BVb19yzVNa360ikXhguGhpjxl8OQ00AbxYeF0vK0/KkNd.wep2', true);
insert into user_role (user_id, role_id) values (250, 1);
-- pass: Nsuzivo.rs123*
insert into user_credentials (id, username, password, verified) values (251, 'nsukrivo', '$2a$10$41qb5IEzPDt6rrXb8TGA9ej8NgP49iK4iwCQd.OkFeB.Hqg7ocQ7u', true);
insert into user_role (user_id, role_id) values (251, 1);
-- pass: Nsukrivo123*
insert into user_credentials (id, username, password, verified) values (252, 'novisadnocu', '$2a$10$YegbzmKoEVmeDNVuCDQOOOza4KVv7x0hsf6e7FGhHMwaUVOIEFGwC', true);
insert into user_role (user_id, role_id) values (252, 1);
-- pass: Novisadnocu123*


-- AGENT
insert into user_credentials (id, username, password, verified) values (301, 'nike', '$2a$10$OD1KiQdb8j7b1yPuzC3NQO8TnhCKaxe1zWc3.QgHBEH0y7hN6G7Ia', true);
insert into user_role (user_id, role_id) values (301, 3);
-- pass : Nike123*
insert into user_credentials (id, username, password, verified) values (302, 'samsung', '$2a$10$jDvsku0joAHAfQk9i2Le4.AoW2jsVb5Zcsyx8ieR7hAbuTNl9MsGe', true);
insert into user_role (user_id, role_id) values (302, 3);
-- pass: Samsung123*