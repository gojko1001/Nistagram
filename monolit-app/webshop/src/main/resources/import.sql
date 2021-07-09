-- Roles
insert into roles (id, name) values (1, "ROLE_USER");
insert into roles (id, name) values (2, "ROLE_AGENT");

-- Permissions
insert into permission (id, name) values (10, 'CREATE_CAMPAIGN');
insert into permission (id, name) values (11, 'UPLOAD_IMG');

insert into roles_permission (role_id, permission_id) values (2, 10);
insert into roles_permission (role_id, permission_id) values (2, 11);


-- Agents
insert into app_user (username, full_name, email, phone, web_site, bio) values ('zara', 'Zara Official', 'zara@mailinator.com', '062/133-05-55', 'https://www.zara.com', 'Zara Official Website');
insert into user_credentials (username, password, verified, is_deactivated, role_id) values ('zara', 'Zara123*', true, false, 2);
insert into app_user (username, full_name, email, phone, web_site, bio) values ('adidas', 'Adidas', 'adidas@mailinator.com', '063/119-00-35', '', '');
insert into user_credentials (username, password, verified, is_deactivated, role_id) values ('adidas', 'Adidas123*', true, false, 2);
insert into app_user (username, full_name, email, phone, web_site, bio) values ('nike', 'Nike', 'nike@mailinator.com', '062/88-121-35', '', 'Just do It');
insert into user_credentials (username, password, verified, is_deactivated, role_id) values ('nike', 'Nike123*', true, false, 2);
-- Users
insert into app_user (username, full_name, email, phone, web_site, bio) values ('maja', 'Maja Stamenic', 'maja@mailinator.com', '063/778-55-55', '', '');
insert into user_credentials (username, password, verified, is_deactivated, role_id) values ('maja', 'Maja123*', true, false, 1);
insert into app_user (username, full_name, email, phone, web_site, bio) values ('nikola', 'Nikola Nikolic', 'nikola@mailinator.com', "063/119-00-35", '', '');
insert into user_credentials (username, password, verified, is_deactivated, role_id) values ('nikola', 'Nikola123*', true, false, 1);


-- Items
insert into item(username, name, description, file_name, on_sale, price, quantity) values ('zara', 'Floral print dress', 'white floaral dress for summer', 'dress1.jpg', false, 50.0, 4);
insert into item(username, name, description, file_name, on_sale, price, quantity) values ('zara', 'Zara famous shoes', 'For every day. Fake leather.', 'shoes1.jpg', false, 30.0, 8);
insert into item(username, name, description, file_name, on_sale, price, quantity) values ('adidas', 'Adidas superstar', 'Adidas originals superstar running shoe', 'shoes2.jpg', false, 90.0, 10);
insert into item(username, name, description, file_name, on_sale, price, quantity) values ('adidas', 'Adidas sneakers', 'Made by Adidas', 'shoes3.jpg', true, 120.0, 3);
