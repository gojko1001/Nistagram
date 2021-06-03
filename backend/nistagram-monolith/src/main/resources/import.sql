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
insert into hashtag(name) values("pokemon");
insert into hashtag(name) values("pikachu");
insert into hashtag(name) values("life");
insert into hashtag(name) values("photooftheday");
insert into hashtag(name) values("fashion");
insert into hashtag(name) values("beautiful");
insert into hashtag(name) values("friends");
insert into hashtag(name) values("strong");
insert into hashtag(name) values("pineapple");
insert into hashtag(name) values("sponge");
insert into hashtag(name) values("marvel");

-- Location
insert into location(name) values ("The Dunavska street");
insert into location(name) values ("St Sava Church");
insert into location(name) values ("Novi Sad, Serbia");
insert into location(name) values ("Belgrade Beer Fest");
insert into location(name) values ("Gardos");
insert into location(name) values ("Disneyland, California");
insert into location(name) values ("Pacific Ocean");

-- MEDIA
-- Gojko's posts
-- 1
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("pokemone biram tebe", "pokemon1.png", true, "gojko", 3, "2021-06-01");
insert into post (media_id) values (1);
insert into media_hashtags (media_id, hashtags_id) values (1, 1);
insert into media_hashtags (media_id, hashtags_id) values (1, 2);
insert into media_hashtags (media_id, hashtags_id) values (1, 3);
-- 2
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("ovo je squirtle", "pokemon2.jpg", true, "gojko", 6, "2021-06-06");
insert into post (media_id) values (2);
insert into media_hashtags (media_id, hashtags_id) values (2, 1);
insert into media_hashtags (media_id, hashtags_id) values (2, 4);
insert into media_hashtags (media_id, hashtags_id) values (2, 8);
--Maja's posts
-- 3
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("Ko zivi u ananusu na dnu mora?", "spongebob1.jpg", true, "maja", 7, "2021-06-02");
insert into post (media_id) values (3);
insert into media_hashtags (media_id, hashtags_id) values (3, 9);
insert into media_hashtags (media_id, hashtags_id) values (3, 2);
insert into media_hashtags (media_id, hashtags_id) values (3, 10);
-- 4
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("moji drugariiiiii", "spongebob2.jpg", true, "maja", 7, "2021-06-03");
insert into post (media_id) values (4);
insert into media_hashtags (media_id, hashtags_id) values (4, 4);
insert into media_hashtags (media_id, hashtags_id) values (4, 7);
insert into media_hashtags (media_id, hashtags_id) values (4, 9);
-- 5
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("pcelica maja", "maya1.jpg", true, "maja", 1, "2021-06-08");
insert into post (media_id) values (5);
insert into media_hashtags (media_id, hashtags_id) values (5, 4);
insert into media_hashtags (media_id, hashtags_id) values (5, 6);
-- Tasa's posts
-- 6
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("Pisite ispod koji je vas omiljeni marvelov lik :)", "marvel1.jpg", true, "tasa", 4, "2021-05-25");
insert into post (media_id) values (6);
insert into media_hashtags (media_id, hashtags_id) values (6, 11);
insert into media_hashtags (media_id, hashtags_id) values (6, 7);
insert into media_hashtags (media_id, hashtags_id) values (6, 8);
-- Masa's posts
-- 7
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("naruto, sakura, saske", "naruto1.jpeg", true, "masa", 5, "2021-04-30");
insert into post (media_id) values (7);
insert into media_hashtags (media_id, hashtags_id) values (7, 7);
insert into media_hashtags (media_id, hashtags_id) values (7, 8);
-- 8
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("GOT <3", "got1.jpg", true, "masa", 6, "2021-05-03");
insert into post (media_id) values (8);
insert into media_hashtags (media_id, hashtags_id) values (8, 1);
insert into media_hashtags (media_id, hashtags_id) values (8, 2);
insert into media_hashtags (media_id, hashtags_id) values (8, 3);
-- 9
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("ko je gledao got?", "got2.jpg", true, "masa", 1, "2021-05-15");
insert into post (media_id) values (9);
insert into media_hashtags (media_id, hashtags_id) values (9, 7);
insert into media_hashtags (media_id, hashtags_id) values (9, 10);
insert into media_hashtags (media_id, hashtags_id) values (9, 11);
-- 10
insert into media (description, file_name, is_image, username, location_id, timestamp) values ("ja biram tebeee", "pokemon5.jpg", true, "masa", 4, "2021-06-01");
insert into post (media_id) values (10);
insert into media_hashtags (media_id, hashtags_id) values (10, 6);
insert into media_hashtags (media_id, hashtags_id) values (10, 4);
insert into media_hashtags (media_id, hashtags_id) values (10, 1);
---

-- LIKES
insert into likes(username, liked, timestamp) values ("masa", true, "2021-06-01");
insert into post_likes(post_id, likes_id) values (1, 1);
insert into likes(username, liked, timestamp) values ("maja", true, "2021-06-03");
insert into post_likes(post_id, likes_id) values (1, 2);
insert into likes(username, liked, timestamp) values ("gojko", true, "2021-06-06");
insert into post_likes(post_id, likes_id) values (2, 3);
insert into likes(username, liked, timestamp) values ("maja", true, "2021-06-03");
insert into post_likes(post_id, likes_id) values (3, 4);
insert into likes(username, liked, timestamp) values ("masa", true, "2021-06-03");
insert into post_likes(post_id, likes_id) values (4, 5);
insert into likes(username, liked, timestamp ) values ("tasa", true, "2021-06-03");
insert into post_likes(post_id, likes_id) values (4, 6);
insert into likes(username, liked, timestamp) values ("gojko", true, "2021-06-05");
insert into post_likes(post_id, likes_id) values (4, 7);
insert into likes(username, liked, timestamp) values ("masa", true, "2021-06-08");
insert into post_likes(post_id, likes_id) values (5, 8);
insert into likes(username, liked, timestamp) values ("tasa", true, "2021-06-09");
insert into post_likes(post_id, likes_id) values (5, 9);
insert into likes(username, liked, timestamp) values ("maja", true, "2021-05-25");
insert into post_likes(post_id, likes_id) values (6, 10);
insert into likes(username, liked, timestamp) values ("masa", true, "2021-05-25");
insert into post_likes(post_id, likes_id) values (6, 11);
insert into likes(username, liked, timestamp) values ("gojko", true, "2021-05-27");
insert into post_likes(post_id, likes_id) values (6, 12);
-----

-- COMMENTS
insert into comment (text, username, timestamp) values ("bla bla bla komentar", "maja", "2021-06-03");
insert into post_comments (post_id, comments_id) values (1, 1);
insert into comment (text, username, timestamp) values ("pikacuu mi je omiljen pokemon!!", "tasa", "2021-06-02");
insert into post_comments (post_id, comments_id) values (1, 2);
insert into comment (text, username, timestamp) values ("gojko radi projekat", "tasa", "2021-06-06");
insert into post_comments (post_id, comments_id) values (2, 3);
insert into comment (text, username, timestamp) values ("sundjer bob kockalone haha", "masa", "2021-06-03");
insert into post_comments (post_id, comments_id) values (3, 4);
insert into comment (text, username, timestamp ) values ("BALONCICI BALONCICI", "tasa", "2021-06-04");
insert into post_comments (post_id, comments_id) values (3, 5);
insert into comment (text, username, timestamp) values ("majica vrednica", "gojko", "2021-06-08");
insert into post_comments (post_id, comments_id) values (5, 6);
insert into comment (text, username, timestamp) values ("maja radi logovanje preko drustvenih mreza vuhuuu", "masa", "2021-06-08");
insert into post_comments (post_id, comments_id) values (5, 7);
insert into comment (text, username, timestamp) values ("meni black widow", "maja", "2021-05-25");
insert into post_comments (post_id, comments_id) values (6, 8);
insert into comment (text, username, timestamp) values ("alo spajdermen je zakon", "gojko", "2021-05-27");
insert into post_comments (post_id, comments_id) values (6, 9);
insert into comment (text, username, timestamp) values ("iron man ljudii", "tasa", "2021-05-27");
insert into post_comments (post_id, comments_id) values (6, 10);
-----






