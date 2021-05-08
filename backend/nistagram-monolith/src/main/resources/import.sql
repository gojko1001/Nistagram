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
insert into permission (id, name) values (99, "AGENT_TEST"); -- TODO: Delete after demonstration

--insert into roles_permission (role_id, permission_id) values (4, 1);
insert into roles_permission (role_id, permission_id) values (1, 11);
insert into roles_permission (role_id, permission_id) values (2, 10);
insert into roles_permission (role_id, permission_id) values (3, 99); -- TODO: Delete after demonstration


-- User Credentials : ADMIN
insert into user_credentials (id, username, password, salt, verified) values (500, 'lubura', '$2a$10$s3iq1XL.6BS4ckuXO5flkOvwghjLs/ImwrYpY9RRuJXDFs9Z8qjV.', 'lCIdtNAxgrb3bx4V8DVyyg==', true);
insert into user_role (user_id, role_id) values (500, 2);
-- pass: Lubura123*

-- USER
insert into user_credentials (id, username, password, salt, verified) values (550, 'maja', '$2a$10$awcKLtXVr3yW6eTjoYfSY.0Dnnpgz0WkjeY3Q63TuNa2z3xdeYJo6', 'VTOgq56dx/7hcSbpKeBh', true);
insert into user_role (user_id, role_id) values (550, 1);
-- pass: Maja123*
insert into user_credentials (id, username, password, salt, verified) values (551, 'tasa', '$2a$10$NIxgOguWYESwXmVYSEx/K.10C2UyuN..u/CL9ObBC/iQaOGZZ8FtO', 'pJbM+eo3RW2k06RA4+1pSg==', true);
insert into user_role (user_id, role_id) values (551, 1);
-- pass: Tasa123*
insert into user_credentials (id, username, password, salt, verified) values (552, 'masa', '$2a$10$jIXJCEzeey1J7ju2EJF24e/Bptz0vAsPB8AmgBB6spPTfjhNLqRpW', 'Gje7rw7nAEYo4zznLu5vMw==', true);
insert into user_role (user_id, role_id) values (552, 1);
-- pass: Masa123*
insert into user_credentials (id, username, password, salt, verified) values (553, 'gojko', '$2a$10$aJHEn4Tr8f.ztJ3OnKnZ3endkbaN1ioLZxPmziVy/JIK/Sraaenxa', 'yFk7WWtK/hSjUPxV2Trt2Q==', true);
insert into user_role (user_id, role_id) values (553, 1);
-- pass: Gojko123*
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('maja', "Maja Stamenic", "maja@mailinator.com", "063/778-55-55", null, null, "1998-11-05", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('tasa', "Nastasja Damjanac", "tasa@mailinator.com", "066/522-63-63", null, null, "1998-08-10", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('masa', "Marija Milanovic", "masa@mailinator.com", "065/121-84-75", null, null, "1998-12-28", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('gojko', "Gojko Novcic", "gojko@mailinator.com", "062/963-22-56", null, null, "1998-04-09", 0, false, true, true);

-- AGENT
insert into user_credentials (id, username, password, salt, verified) values (700, 'nike', '$2a$10$OD1KiQdb8j7b1yPuzC3NQO8TnhCKaxe1zWc3.QgHBEH0y7hN6G7Ia', 'HuQJDpdXDDKsMRDjZmSocg==', true);
insert into user_role (user_id, role_id) values (700, 3);
-- pass : Nike123*
insert into user_credentials (id, username, password, salt, verified) values (701, 'samsung', '$2a$10$jDvsku0joAHAfQk9i2Le4.AoW2jsVb5Zcsyx8ieR7hAbuTNl9MsGe', '8npMS7RVvWBA84P1tUqC3g==', true);
insert into user_role (user_id, role_id) values (701, 3);
-- pass: Samsung123*

insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('nike', 'Nike', "nike@mailinator.com", "062/963-22-56", 'www.nike.com', 'Just Do It!', "1975-06-09", 2, true, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('samsung', 'Samsung', "samsung@mailinator.com", "062/963-22-56", 'www.samsung.com', 'Be inspired to #DoWhatYouCant', "1990-12-08", 2, true, true, true);


-- Tag
insert into tag(name) values("life");
insert into tag(name) values("love");
insert into tag(name) values("dream");
insert into tag(name) values("car");
insert into tag(name) values("photooftheday");
insert into tag(name) values("fashion");
insert into tag(name) values("beautiful");
insert into tag(name) values("friends");

-- Location
insert into location(name) values ("The Dunavska street");
insert into location(name) values ("St Sava Church");
insert into location(name) values ("Novi Sad, Serbia");
insert into location(name) values ("Belgrade Beer Fest");
insert into location(name) values ("Gardos");

