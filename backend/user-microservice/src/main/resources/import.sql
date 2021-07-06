-- UserCategory
insert into user_category (name) values ('influencer');
insert into user_category (name) values ('media');
insert into user_category (name) values ('business');
insert into user_category (name) values ('brand');
insert into user_category (name) values ('organisation');
insert into user_category (name) values ('actor');
insert into user_category (name) values ('musician');

-- VerficationRequests
insert into verification_request (file_path, status, category_id) values ('id1.jpg', 1, 1);
insert into verification_request (file_path, status, category_id) values ('id2.jpg', 1, 1);
insert into verification_request (file_path, status, category_id) values ('id3.jpg', 1, 4);
insert into verification_request (file_path, status, category_id) values ('id4.jpg', 1, 4);

-- User
-- Role:admin
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (100, 'lubura', "Nikola Luburic", "lubura@mailinator.com", "063/000-55-55", null, null, "1988-11-05", 1, true, true, true);
-- Role:user
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable, verification_request_id) values (101, 'maja', "Maja Stamenic", "maja@mailinator.com", "063/778-55-55", null, null, "1998-11-05", 1, true, true, true, 1);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (102, 'tasa', "Nastasja Damjanac", "tasa@mailinator.com", "066/522-63-63", null, null, "1998-08-10", 1, false, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (103, 'masa', "Marija Milanovic", "masa@mailinator.com", "065/121-84-75", null, null, "1998-12-28", 1, false, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (104, 'gojko', "Gojko Novcic", "gojko@mailinator.com", "062/963-22-56", null, null, "1998-04-09", 0, false, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable, verification_request_id) values (105, 'mark', "Mark Zukenberg", "mark@mailinator.com", "069/689-55-47",'www.facebook.com', "Founder of Facebook", "1984-05-14", 0, true, true, true, 2);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (106, 'mare', "Marko Milosevic", "marko@mailinator.com", "064/225-22-22", null, "Boxing", "2001-04-14", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (107, 'milica', "Milica Milanovic", "milica@mailinator.com", "063/212-20-00", null, "Makeup by milica", "2000-08-15", 1, false, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (108, 'jelena', "Jelena Jovanovic", "jelena@mailinator.com", "062/287-20-01", null, "Novi Sad", "2006-01-11", 1, false, true, false);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (109, 'jovana', "Jovana Jankovic", "jovana@mailinator.com", "062/287-20-01", null, "FTN - E2", "1994-02-15", 1, true, false, false);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (110, 'milos', "Milos Mitrovic", "milos@mailinator.com", "063/48-88-019", null, "FTN - E1", "1995-02-01", 0, false, false, false);


insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (201, 'jason', "Jason Statham", "jason@mailinator.com", "+47 55/47-888-654", 'www.imdb.com/name/nm0005458/', 'Acion Movie Star', "1967-06-26", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (202, 'josh', "Josh Brolin", "josh@mailinator.com", "+49 52/887-98-97", 'www.imdb.com/name/nm0000982/', 'Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better. — Beckett', "1968-02-12", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (203, 'therock', "Dwayne Johnson", "rock@mailinator.com", "+49 78/998-35-25", 'www.imdb.com/name/nm0425005', 'Rocks!', "1972-05-02", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (204, 'margarot', "Margarot Robbie", "margarot@mailinator.com", "+77 89/9111-5-77", 'www.imdb.com/name/nm3053338', 'Big dreams for big future', "1990-07-02", 1, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (205, 'galg', "Gal Gadot", "gal@mailinator.com", "+45 12/744-55-636", 'www.imdb.com/name/nm2933757', null, "1985-04-30", 1, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (206, 'tomcruise', "Tom Cruise", "cruise@mailinator.com", "+44 45/44-55-666", 'tomcruise.com', 'Actor. Producer. Running in movies since 1981.', "1962-07-03", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (207, 'tomholland', "Tom Holland", "tom@mailinator.com", "+98 22/66-55-444", 'www.thebrotherstrust.shop/shop', 'Life is good', "1996-05-01", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable, profile_pic_path) values (208, 'johncena', "John Cena", "john@mailinator.com", "+45 55/123-45-789", 'www.imdb.com/name/nm1078479/', 'Ex-wrestler', "1977-04-23", 0, true, true, true, "208.jpg");

insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (250, 'nsuzivo.rs', "Ns Uzivo", "nsuzivo@mailinator.com", "+381 66/886-44-23", 'www.nsuzivo.rs', 'Najbrži portal u gradu', "2015-01-23", 0, true, true, true);
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable, profile_pic_path) values (251, 'nsukrivo', "Ns Ukrivo", "nsukrivo@mailinator.com", "+381 65/45-777-99", null, 'Дебилана у најлепшем граду Србије', "2016-01-23", 0, true, true, true, "251.jpg");
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values (252, 'novisadnocu', "NoviSadNocu", "novisadnocu@mailinator.com", "+381 67/774-45-45", 'www.novisadnocu.com', 'Experience the best nightlife in Novi Sad', "2009-01-01", 0, true, true, true);


-- Role:agent
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable, verification_request_id, profile_pic_path) values (301, 'nike', 'Nike', "nike@mailinator.com", "062/963-22-56", 'www.nike.com', 'Just Do It!', "1975-06-09", 2, true, true, true, 3, "301.jpg");
insert into app_user (id, username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable, verification_request_id) values (302, 'samsung', 'Samsung', "samsung@mailinator.com", "062/963-22-56", 'www.samsung.com', 'Be inspired to #DoWhatYouCant', "1990-12-08", 2, true, true, true, 4);


-- UserRelation
-- maja
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 104, 2, false, false, true, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 102, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 103, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 106, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 205, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 204, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 301, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (101, 302, 1, false, false, false, false);

-- tasa
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 101, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 103, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 104, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 108, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 109, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 201, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 207, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 208, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 251, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 301, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (102, 302, 1, false, false, false, false);

-- masa
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 101, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 102, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 104, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 105, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 107, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 108, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 205, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 206, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 207, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 208, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (103, 252, 1, false, false, false, false);

-- gojko
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 101, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 102, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 103, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 108, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 109, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 110, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 201, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 202, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 203, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 206, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 250, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (104, 251, 1, false, false, false, false);

-- mark
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 201, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 202, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 203, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 204, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 205, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 206, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (105, 207, 1, false, false, false, false);

-- mare
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 102, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 103, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 104, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 105, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 106, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 107, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 108, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 250, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (106, 251, 1, false, false, false, false);

-- milica
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 101, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 102, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 103, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 104, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 105, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 106, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 108, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 201, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 202, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 203, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 204, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 205, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 206, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (107, 207, 1, false, false, false, false);


-- jelena
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 101, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 102, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 103, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 104, 2, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 105, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 106, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 107, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 110, 0, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 251, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 252, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (108, 250, 1, false, false, false, false);

-- jovana
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 101, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 103, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 105, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 106, 3, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 107, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 108, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 110, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 202, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 204, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 205, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 206, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (109, 208, 1, false, false, false, false);

-- milos
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 101, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 102, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 103, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 104, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 105, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 106, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 107, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 108, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 109, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 201, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 202, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 203, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 204, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 205, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 206, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 207, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 208, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 250, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 251, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 252, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 301, 1, false, false, false, false);
insert into user_relation (user_id, related_user_id, relation_status, mute_post, mute_story, notify_post, notify_story) values (110, 302, 1, false, false, false, false);