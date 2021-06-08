-- User
-- Role:user
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (1, 'maja', "Maja Stamenic", "maja@mailinator.com", "063/778-55-55", null, null, "1998-11-05", 1, false, true, true);
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (2, 'tasa', "Nastasja Damjanac", "tasa@mailinator.com", "066/522-63-63", null, null, "1998-08-10", 1, false, true, true);
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (3, 'masa', "Marija Milanovic", "masa@mailinator.com", "065/121-84-75", null, null, "1998-12-28", 1, false, true, true);
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (4, 'gojko', "Gojko Novcic", "gojko@mailinator.com", "062/963-22-56", null, null, "1998-04-09", 0, false, true, true);
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (5, 'mark', "Mark Zukenber", "mark@mailinator.com", "069/689-55-47", "www.facebook.com", "Founder of Facebook", "1984-05-14", 0, true, true, true);

-- Role:agent
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (6, 'nike', 'Nike', "nike@mailinator.com", "062/963-22-56", 'www.nike.com', 'Just Do It!', "1975-06-09", 2, true, true, true);
insert into users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (7, 'samsung', 'Samsung', "samsung@mailinator.com", "062/963-22-56", 'www.samsung.com', 'Be inspired to #DoWhatYouCant', "1990-12-08", 2, true, true, true);


-- UserRelation
-- maja
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (1, 4, false, 1);
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (1, 2, false, 3);
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (1, 3, false, 0);
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (1, 6, false, 1);
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (1, 7, false, 1);

-- to maja
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (4, 1, false, 0);
insert into user_relation (user_id, related_user_id, enable_notification, relation_status) values (3, 1, false, 1);
