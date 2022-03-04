INSERT INTO users (id, username, password, enabled) VALUES (1, 'admin', '123456', TRUE);

INSERT INTO roles (id, name) VALUES (1, 'sysadmin');
INSERT INTO roles (id, name) VALUES (2, 'project.user');

INSERT INTO permissions (id, name) VALUES (1, 'users.create');
INSERT INTO permissions (id, name) VALUES (2, 'users.delete');
INSERT INTO permissions (id, name) VALUES (3, 'users.list');
INSERT INTO permissions (id, name) VALUES (4, 'users.update');


INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);