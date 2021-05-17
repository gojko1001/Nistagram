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

--insert into roles_permission (role_id, permission_id) values (4, 1);
insert into roles_permission (role_id, permission_id) values (1, 11);
insert into roles_permission (role_id, permission_id) values (2, 10);


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
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('maja', "Maja Stamenic", "maja@mailinator.com", "063/778-55-55", null, null, "1998-11-05", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('tasa', "Nastasja Damjanac", "tasa@mailinator.com", "066/522-63-63", null, null, "1998-08-10", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('masa', "Marija Milanovic", "masa@mailinator.com", "065/121-84-75", null, null, "1998-12-28", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('gojko', "Gojko Novcic", "gojko@mailinator.com", "062/963-22-56", null, null, "1998-04-09", 0, false, true, true);

-- AGENT
insert into user_credentials (id, username, password, verified) values (700, 'nike', '$2a$10$OD1KiQdb8j7b1yPuzC3NQO8TnhCKaxe1zWc3.QgHBEH0y7hN6G7Ia', true);
insert into user_role (user_id, role_id) values (700, 3);
-- pass : Nike123*
insert into user_credentials (id, username, password, verified) values (701, 'samsung', '$2a$10$jDvsku0joAHAfQk9i2Le4.AoW2jsVb5Zcsyx8ieR7hAbuTNl9MsGe', true);
insert into user_role (user_id, role_id) values (701, 3);
-- pass: Samsung123*

insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('nike', 'Nike', "nike@mailinator.com", "062/963-22-56", 'www.nike.com', 'Just Do It!', "1975-06-09", 2, true, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('samsung', 'Samsung', "samsung@mailinator.com", "062/963-22-56", 'www.samsung.com', 'Be inspired to #DoWhatYouCant', "1990-12-08", 2, true, true, true);


-- Tag
insert into tag(name) values("pokemon");
insert into tag(name) values("pikachu");
insert into tag(name) values("life");
insert into tag(name) values("photooftheday");
insert into tag(name) values("fashion");
insert into tag(name) values("beautiful");
insert into tag(name) values("friends");
insert into tag(name) values("strong");
insert into tag(name) values("pineapple");
insert into tag(name) values("sponge");
insert into tag(name) values("marvel");

-- Location
insert into location(name) values ("The Dunavska street");
insert into location(name) values ("St Sava Church");
insert into location(name) values ("Novi Sad, Serbia");
insert into location(name) values ("Belgrade Beer Fest");
insert into location(name) values ("Gardos");
insert into location(name) values ("Disneyland, California");
insert into location(name) values ("Pacific Ocean");

--POSTS
    -- Gojko's posts
    insert into image (description, file_name, username, location_id) values ("pokemone biram tebe", "pokemon1.png", "gojko", 3);
    insert into image_tags (image_id, tags_id) values (1, 1);
    insert into image_tags (image_id, tags_id) values (1, 2);
    insert into image_tags (image_id, tags_id) values (1, 3);
    insert into image (description, file_name, username, location_id) values ("ovo je squirtle", "pokemon2.jpg", "gojko", 6);
    insert into image_tags (image_id, tags_id) values (2, 1);
    insert into image_tags (image_id, tags_id) values (2, 4);
    insert into image_tags (image_id, tags_id) values (2, 8);
    -- Maja's posts
    insert into image (description, file_name, username, location_id) values ("Ko zivi u ananusu na dnu mora?", "spongebob1.jpg", "maja", 7);
    insert into image_tags (image_id, tags_id) values (3, 9);
    insert into image_tags (image_id, tags_id) values (3, 2);
    insert into image_tags (image_id, tags_id) values (3, 10);
    insert into image (description, file_name, username, location_id) values ("moji drugariiiiii", "spongebob2.jpg", "maja", 7);
    insert into image_tags (image_id, tags_id) values (4, 4);
    insert into image_tags (image_id, tags_id) values (4, 7);
    insert into image_tags (image_id, tags_id) values (4, 9);
    insert into image (description, file_name, username, location_id) values ("pcelica maja", "maya1.jpg", "maja", 1);
    insert into image_tags (image_id, tags_id) values (5, 4);
    insert into image_tags (image_id, tags_id) values (5, 6);
    -- Tasa's posts
    insert into image (description, file_name, username, location_id) values ("Pisite ispod koji je vas omiljeni marvelov lik :)", "marvel1.jpg", "tasa", 4);
    insert into image_tags (image_id, tags_id) values (6, 11);
    insert into image_tags (image_id, tags_id) values (6, 7);
    insert into image_tags (image_id, tags_id) values (6, 8);
    -- Masa's posts
    insert into image (description, file_name, username, location_id) values ("naruto, sakura, saske", "naruto1.jpeg", "masa", 5);
    insert into image_tags (image_id, tags_id) values (7, 7);
    insert into image_tags (image_id, tags_id) values (7, 8);
-----

-- COMMENTS
    insert into comment (text, username) values ("bla bla bla komentar", "maja")
    insert into image_comments (image_id, comments_id) values (1, 1);
    insert into comment (text, username) values ("pikacuu mi je omiljen pokemon!!", "tasa")
    insert into image_comments (image_id, comments_id) values (1, 2);
    insert into comment (text, username) values ("gojko radi projekat", "tasa")
    insert into image_comments (image_id, comments_id) values (2, 3);
    insert into comment (text, username) values ("sundjer bob kockalone haha", "masa")
    insert into image_comments (image_id, comments_id) values (3, 4);
    insert into comment (text, username) values ("BALONCICI BALONCICI", "tasa")
    insert into image_comments (image_id, comments_id) values (3, 5);
    insert into comment (text, username) values ("majica vrednica", "gojko")
    insert into image_comments (image_id, comments_id) values (5, 6);
    insert into comment (text, username) values ("maja radi logovanje preko drustvenih mreza vuhuuu", "masa")
    insert into image_comments (image_id, comments_id) values (5, 7);
    insert into comment (text, username) values ("meni black widow", "maja")
    insert into image_comments (image_id, comments_id) values (6, 8);
    insert into comment (text, username) values ("alo spajdermen je zakon", "gojko")
    insert into image_comments (image_id, comments_id) values (6, 8);
    insert into comment (text, username) values ("iron man ljudii", "tasa")
    insert into image_comments (image_id, comments_id) values (6, 8);
-----






