CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    username VARCHAR(32) NOT NULL,
    password VARCHAR(255) NOT NULL,

    enabled BOOLEAN NOT NULL,

    constraint uc_users unique (username)
);

CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    name VARCHAR(128) NOT NULL,

    constraint uc_roles unique (name)
);

CREATE TABLE permissions (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    name VARCHAR(128) NOT NULL,

    constraint uc_permissions unique (name)
);

CREATE TABLE user_roles (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    constraint uc_user_roles unique (user_id, role_id)
);

CREATE TABLE user_permissions (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,

    constraint uc_user_permissions unique (user_id, permission_id)
);
