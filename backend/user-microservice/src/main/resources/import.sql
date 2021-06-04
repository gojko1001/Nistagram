-- User
insert into nistagramdb.users (id,username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (1, 'maja', "Maja Stamenic", "maja@mailinator.com", "063/778-55-55", null, null, "1998-11-05", 1, false, true, true);
insert into nistagramdb.users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable)values (2, 'tasa', "Nastasja Damjanac", "tasa@mailinator.com", "066/522-63-63", null, null, "1998-08-10", 1, false, true, true);
insert into nistagramdb.users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable)values (3, 'masa', "Marija Milanovic", "masa@mailinator.com", "065/121-84-75", null, null, "1998-12-28", 1, false, true, true);
insert into nistagramdb.users (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable)values (4, 'gojko', "Gojko Novcic", "gojko@mailinator.com", "062/963-22-56", null, null, "1998-04-09", 0, false, true, true);

-- UserRelation
-- maja
insert into nistagramdb.user_relation (related_user_id, user_id, enable_notification, relation_status) values (4, 1, false, 1);
insert into nistagramdb.user_relation (related_user_id, user_id, enable_notification, relation_status) values (2, 1, false, 3);
insert into nistagramdb.user_relation (related_user_id, user_id, enable_notification, relation_status) values (3, 1, false, 0);
-- to maja
insert into nistagramdb.user_relation (related_user_id, user_id, enable_notification, relation_status) values (1, 4, false, 0);
insert into nistagramdb.user_relation (related_user_id, user_id, enable_notification, relation_status) values (1, 3, false, 1);