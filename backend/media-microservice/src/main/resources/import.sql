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
        insert into media (description, file_name, is_image, username, location_id, timestamp) values ("naruto, sakura, saske", "marvel1.jpeg", true, "masa", 5, "2021-04-30");
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
    insert into likes(username, liked, post_id, timestamp) values ("masa", true, 1, "2021-06-01");
    insert into post_likes(post_id, likes_id) values (1, 1);
    insert into likes(username, liked, post_id, timestamp) values ("maja", true, 1, "2021-06-03");
    insert into post_likes(post_id, likes_id) values (1, 2);
    insert into likes(username, liked, post_id, timestamp) values ("gojko", true, 2, "2021-06-06");
    insert into post_likes(post_id, likes_id) values (2, 3);
    insert into likes(username, liked, post_id, timestamp) values ("maja", true, 3, "2021-06-03");
    insert into post_likes(post_id, likes_id) values (3, 4);
    insert into likes(username, liked, post_id, timestamp) values ("masa", true, 4, "2021-06-03");
    insert into post_likes(post_id, likes_id) values (4, 5);
    insert into likes(username, liked, post_id, timestamp ) values ("tasa", true, 4, "2021-06-03");
    insert into post_likes(post_id, likes_id) values (4, 6);
    insert into likes(username, liked, post_id, timestamp) values ("gojko", true, 4, "2021-06-05");
    insert into post_likes(post_id, likes_id) values (4, 7);
    insert into likes(username, liked, post_id, timestamp) values ("masa", true, 5, "2021-06-08");
    insert into post_likes(post_id, likes_id) values (5, 8);
    insert into likes(username, liked, post_id, timestamp) values ("tasa", true, 5, "2021-06-09");
    insert into post_likes(post_id, likes_id) values (5, 9);
    insert into likes(username, liked, post_id, timestamp) values ("maja", true, 6, "2021-05-25");
    insert into post_likes(post_id, likes_id) values (6, 10);
    insert into likes(username, liked, post_id, timestamp) values ("masa", true, 6, "2021-05-25");
    insert into post_likes(post_id, likes_id) values (6, 11);
    insert into likes(username, liked, post_id, timestamp) values ("gojko", true, 6, "2021-05-27");
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