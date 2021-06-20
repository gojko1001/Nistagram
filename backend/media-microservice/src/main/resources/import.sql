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
insert into media_name(file_name, is_image) values ("pokemon1.png", true);
insert into media (description, username, location_id, timestamp) values ("pokemone biram tebe", "gojko", 3, "2021-06-01");
insert into media_media_name(media_id, media_name_id) values (1, 1);
insert into post (media_id) values (1);
insert into media_hashtags (media_id, hashtags_id) values (1, 1);
insert into media_hashtags (media_id, hashtags_id) values (1, 2);
insert into media_hashtags (media_id, hashtags_id) values (1, 3);
-- 2
insert into media_name(file_name, is_image) values ("pokemon2.jpg", true);
insert into media (description, username, location_id, timestamp) values ("ovo je squirtle", "gojko", 6, "2021-06-06");
insert into media_media_name(media_id, media_name_id) values (2, 2);
insert into post (media_id) values (2);
insert into media_hashtags (media_id, hashtags_id) values (2, 1);
insert into media_hashtags (media_id, hashtags_id) values (2, 4);
insert into media_hashtags (media_id, hashtags_id) values (2, 8);
--Maja's posts
-- 3
insert into media_name(file_name, is_image) values ("spongebob1.jpg", true);
insert into media (description, username, location_id, timestamp) values ("Ko zivi u ananusu na dnu mora?", "maja", 7, "2021-06-02");
insert into media_media_name(media_id, media_name_id) values (3, 3);
insert into post (media_id) values (3);
insert into media_hashtags (media_id, hashtags_id) values (3, 9);
insert into media_hashtags (media_id, hashtags_id) values (3, 2);
insert into media_hashtags (media_id, hashtags_id) values (3, 10);
-- 4
insert into media_name(file_name, is_image) values ("spongebob2.jpg", true);
insert into media (description, username, location_id, timestamp) values ("moji drugariiiiii", "maja", 7, "2021-06-03");
insert into media_media_name(media_id, media_name_id) values (4, 4);
insert into post (media_id) values (4);
insert into media_hashtags (media_id, hashtags_id) values (4, 4);
insert into media_hashtags (media_id, hashtags_id) values (4, 7);
insert into media_hashtags (media_id, hashtags_id) values (4, 9);
-- 5
insert into media_name(file_name, is_image) values ("maya1.jpg", true);
insert into media (description, username, location_id, timestamp) values ("pcelica maja", "maja", 1, "2021-06-08");
insert into media_media_name(media_id, media_name_id) values (5, 5);
insert into post (media_id) values (5);
insert into media_hashtags (media_id, hashtags_id) values (5, 4);
insert into media_hashtags (media_id, hashtags_id) values (5, 6);
-- Tasa's posts
-- 6
insert into media_name(file_name, is_image) values ("marvel1.jpg", true);
insert into media (description, username, location_id, timestamp) values ("Pisite ispod koji je vas omiljeni marvelov lik :)", "tasa", 4, "2021-05-25");
insert into media_media_name(media_id, media_name_id) values (6, 6);
insert into post (media_id) values (6);
insert into media_hashtags (media_id, hashtags_id) values (6, 11);
insert into media_hashtags (media_id, hashtags_id) values (6, 7);
insert into media_hashtags (media_id, hashtags_id) values (6, 8);
-- Masa's posts
-- 7
insert into media_name(file_name, is_image) values ("naruto1.jpeg", true);
insert into media (description, username, location_id, timestamp) values ("naruto, sakura, saske", "masa", 5, "2021-04-30");
insert into media_media_name(media_id, media_name_id) values (7, 7);
insert into post (media_id) values (7);
insert into media_hashtags (media_id, hashtags_id) values (7, 7);
insert into media_hashtags (media_id, hashtags_id) values (7, 8);
-- 8
insert into media_name(file_name, is_image) values ("got1.jpg", true);
insert into media (description, username, location_id, timestamp) values ("GOT <3", "masa", 6, "2021-05-03");
insert into media_media_name(media_id, media_name_id) values (8, 8);
insert into post (media_id) values (8);
insert into media_hashtags (media_id, hashtags_id) values (8, 1);
insert into media_hashtags (media_id, hashtags_id) values (8, 2);
insert into media_hashtags (media_id, hashtags_id) values (8, 3);
-- 9
insert into media_name(file_name, is_image) values ("got2.jpg", true);
insert into media (description, username, location_id, timestamp) values ("ko je gledao got?", "masa", 1, "2021-05-15");
insert into media_media_name(media_id, media_name_id) values (9, 9);
insert into post (media_id) values (9);
insert into media_hashtags (media_id, hashtags_id) values (9, 7);
insert into media_hashtags (media_id, hashtags_id) values (9, 10);
insert into media_hashtags (media_id, hashtags_id) values (9, 11);
-- 10
insert into media_name(file_name, is_image) values ("pokemon5.jpg", true);
insert into media (description, username, location_id, timestamp) values ("ja biram tebeee", "masa", 4, "2021-06-01");
insert into media_media_name(media_id, media_name_id) values (10, 10);
insert into post (media_id) values (10);
insert into media_hashtags (media_id, hashtags_id) values (10, 6);
insert into media_hashtags (media_id, hashtags_id) values (10, 4);
insert into media_hashtags (media_id, hashtags_id) values (10, 1);
---

-- LIKES
insert into likes(username, liked, timestamp, post_id) values ("masa", true, "2021-06-01", 1);
insert into post_likes(post_id, likes_id) values (1, 1);
insert into likes(username, liked, timestamp, post_id) values ("maja", true, "2021-06-03", 1);
insert into post_likes(post_id, likes_id) values (1, 2);
insert into likes(username, liked, timestamp, post_id) values ("gojko", true, "2021-06-06", 2);
insert into post_likes(post_id, likes_id) values (2, 3);
insert into likes(username, liked, timestamp,post_id) values ("maja", true, "2021-06-03", 3);
insert into post_likes(post_id, likes_id) values (3, 4);
insert into likes(username, liked, timestamp, post_id) values ("masa", true, "2021-06-03", 4);
insert into post_likes(post_id, likes_id) values (4, 5);
insert into likes(username, liked, timestamp, post_id) values ("tasa", true, "2021-06-03", 4);
insert into post_likes(post_id, likes_id) values (4, 6);
insert into likes(username, liked, timestamp, post_id) values ("gojko", true, "2021-06-05", 4);
insert into post_likes(post_id, likes_id) values (4, 7);
insert into likes(username, liked, timestamp, post_id) values ("masa", true, "2021-06-08", 5);
insert into post_likes(post_id, likes_id) values (5, 8);
insert into likes(username, liked, timestamp, post_id) values ("tasa", true, "2021-06-09", 5);
insert into post_likes(post_id, likes_id) values (5, 9);
insert into likes(username, liked, timestamp, post_id) values ("maja", true, "2021-05-25", 6);
insert into post_likes(post_id, likes_id) values (6, 10);
insert into likes(username, liked, timestamp, post_id) values ("masa", true, "2021-05-25", 6);
insert into post_likes(post_id, likes_id) values (6, 11);
insert into likes(username, liked, timestamp, post_id) values ("gojko", true, "2021-05-27", 6);
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


-- STORIES
-- Gojko's stories
-- 11
insert into media_name(file_name, is_image) values ("waterfall1.jpg", true);
insert into media (description, username, location_id, timestamp) values ("moj prvi story", "gojko", 5, "2021-06-03");
insert into media_media_name(media_id, media_name_id) values (11, 11);
insert into story (media_id, for_close_friends, highlighted) values (11, true, false);
insert into media_hashtags (media_id, hashtags_id) values (11, 7);
insert into media_hashtags (media_id, hashtags_id) values (11, 8);
-- 12
insert into media_name(file_name, is_image) values ("river1.jpg", true);
insert into media (description, username, location_id, timestamp) values ("moj drugiiiii story", "gojko", 6, "2021-06-08");
insert into media_media_name(media_id, media_name_id) values (12, 12);
insert into story (media_id, for_close_friends, highlighted) values (12, false, false);
insert into media_hashtags (media_id, hashtags_id) values (12, 1);
insert into media_hashtags (media_id, hashtags_id) values (12, 2);
---


-- COLLECTIONS
-- Gojko's collections
insert into favourite (post_id) values (5);
insert into favourite (post_id) values (7);
insert into favourite (post_id) values (9);
insert into favourite (post_id) values (10);
insert into collection (name, username) values ("My first collection", "gojko");
insert into collection_favourites (collection_id, favourites_id) values (1, 1);
insert into collection_favourites (collection_id, favourites_id) values (1, 2);
insert into collection (name, username) values ("Fav collection", "gojko");
insert into collection_favourites (collection_id, favourites_id) values (2, 3);
insert into collection_favourites (collection_id, favourites_id) values (2, 4);
-- Maja's collections
insert into favourite (post_id) values (1);
insert into favourite (post_id) values (6);
insert into favourite (post_id) values (7);
insert into collection (name, username) values ("COLLECTION NO.1", "maja");
insert into collection_favourites (collection_id, favourites_id) values (3, 5);
insert into collection_favourites (collection_id, favourites_id) values (3, 6);
insert into collection (name, username) values ("Pcelica maja", "maja");
insert into collection_favourites (collection_id, favourites_id) values (4, 7);
-- Tasa's collections
insert into favourite (post_id) values (1);
insert into favourite (post_id) values (6);
insert into favourite (post_id) values (10);
insert into collection (name, username) values ("collection123", "tasa");
insert into collection_favourites (collection_id, favourites_id) values (5, 8);
insert into collection_favourites (collection_id, favourites_id) values (5, 9);
insert into collection_favourites (collection_id, favourites_id) values (5, 10);
-- Masa's collections
insert into favourite (post_id) values (1);
insert into favourite (post_id) values (4);
insert into favourite (post_id) values (6);
insert into collection (name, username) values ("Fav posts", "masa");
insert into collection_favourites (collection_id, favourites_id) values (6, 11);
insert into collection_favourites (collection_id, favourites_id) values (6, 12);
insert into collection_favourites (collection_id, favourites_id) values (6, 13);
---

-- Inappropriate content

insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(1, "gojko","nikola", 1);
insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(1, "masa","nikola", 2);
insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(1, "tasa","nikola", 3);
insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(1, "tasa","nikola", 4);
insert into inappropriate_content(request_status, requested_by,media_id) values(0, "gojko", 2);
insert into inappropriate_content(request_status, requested_by, media_id) values(0, "tasa", 2);
insert into inappropriate_content(request_status, requested_by, media_id) values(0, "gojko", 4);

insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(1, "gojko","nikola", 8);
insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(0, "masa","nikola", 9);
insert into inappropriate_content(request_status, requested_by, responded_by,media_id) values(2, "tasa","nikola", 5);






