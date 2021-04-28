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
insert into permission (id, name) values (10, "ALL_USERS");
insert into permission (id, name) values (11, "GET_USER");
insert into permission (id, name) values (99, "AGENT_TEST"); -- TODO: Delete after demonstration

insert into roles_permission (role_id, permission_id) values (1, 11);
insert into roles_permission (role_id, permission_id) values (2, 10);
insert into roles_permission (role_id, permission_id) values (3, 99); -- TODO: Delete after demonstration


-- User Credentials : ADMIN
insert into user_credentials (id, username, password, salt, verified) values (500, 'lubura', 'IWAdx77GJPMpCaxWwB80moyj2A782p3fOk10/tbRTa64ZLfWD21smW4zubX+u+DotJ+eZruiRKRm\r\nZMRnqxaZwQ==', 'lCIdtNAxgrb3bx4V8DVyyg==', true);
insert into user_role (user_id, role_id) values (500, 2);
-- pass: Lubura!1

-- USER
insert into user_credentials (id, username, password, salt, verified) values (550, 'maja', 'x1Yj5nGWlCuhfslRRstZf8JMOKjrgYeJa4Q6tAlwftGN1VWWuG69QbXkANjJj4Q/XDnmfhV+wCcN\r\nWcFBmWGPpQ==', 'VTOgq56dx/7hcSbpKeBh', true);
insert into user_role (user_id, role_id) values (550, 1);
-- pass: Maja!123
insert into user_credentials (id, username, password, salt, verified) values (551, 'tasa', 'Nab5/ZN1ogEdQq/EH3srED3eblsJ+jW2J2bwqAirex/Cjv7+mZqvgRL9cBdn0Sd8GZ3HqULNzM7a\r\nzxYWKOCrJQ==', 'pJbM+eo3RW2k06RA4+1pSg==', true);
insert into user_role (user_id, role_id) values (551, 1);
-- pass: Tasa!123
insert into user_credentials (id, username, password, salt, verified) values (552, 'masa', 'tXVU+3jGxhoJco+/yOIEzA+BF8xz9AbefXrJ7FhZkVnHukFneUi5xDHUaXYy0v7z/ELk6tlABulh\r\nEb8hppAqaA==', 'Gje7rw7nAEYo4zznLu5vMw==', true);
insert into user_role (user_id, role_id) values (552, 1);
-- pass: Masa!123
insert into user_credentials (id, username, password, salt, verified) values (553, 'gojko', 'ovj74wZG0AV/3VGQP2SFXUdCCpv0SPZQ6HUJnakEpcdPhh9eXWkDHk8epo6dy4uVZj5M4pJdpfds\r\nOEnaUHw9sA==', 'yFk7WWtK/hSjUPxV2Trt2Q==', true);
insert into user_role (user_id, role_id) values (553, 1);
-- pass: Gojko!12
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('maja', "Maja Stamenic", "maja@mailinator.com", "063/778-55-55", null, null, "1998-11-05", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('tasa', "Nastasja Damjanac", "tasa@mailinator.com", "066/522-63-63", null, null, "1998-08-10", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('masa', "Marija Milanovic", "masa@mailinator.com", "065/121-84-75", null, null, "1998-12-27", 1, false, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('gojko', "Gojko Novcic", "gojko@mailinator.com", "062/963-22-56", null, null, "1998-04-09", 0, false, true, true);

-- AGENT
insert into user_credentials (id, username, password, salt, verified) values (700, 'nike', 'jv1GPCPTZwBM577rNT5dIRAfERRxV4v+SkWfQ/4K+VvTgqyTLBmOKXdGMYvFVNqrbq85Rw3bijHf\r\nY7lHro0/TA==', 'HuQJDpdXDDKsMRDjZmSocg==', true);
insert into user_role (user_id, role_id) values (700, 3);
-- pass : Nike/123
insert into user_credentials (id, username, password, salt, verified) values (701, 'samsung', 'aiFVVbe3byOtT04bdwc5RjeBmwaStQHSr7npIvO+2JgHjRxbI6oYm0fCdSzhh9yO4hpxIYm29iz+\r\n+0X9GzdFKg==', '8npMS7RVvWBA84P1tUqC3g==', true);
insert into user_role (user_id, role_id) values (701, 3);
-- pass: Samsung/1

insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('nike', 'Nike', "nike@mailinator.com", "062/963-22-56", 'www.nike.com', 'Just Do It!', "1975-06-09", 2, true, true, true);
insert into users (username, full_name, email, phone, web_site, bio, birth_date, user_gender, public_profile, publicdm, taggable) values ('samsung', 'Samsung', "samsung@mailinator.com", "062/963-22-56", 'www.samsung.com', 'Be inspired to #DoWhatYouCant', "1990-12-08", 2, true, true, true);
