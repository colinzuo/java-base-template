CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    username VARCHAR(32) NOT NULL,
    password VARCHAR(255) NOT NULL,

    enabled BOOLEAN NOT NULL,

--    constraint pk_users primary key (id),
    constraint uc_users unique (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    name VARCHAR(128) NOT NULL,

    constraint uc_roles unique (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE permissions (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    name VARCHAR(128) NOT NULL,

    constraint uc_permissions unique (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE user_roles (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    constraint uc_user_roles unique (user_id, role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE user_permissions (
    id BIGINT NOT NULL AUTO_INCREMENT primary key,

    user_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,

    constraint uc_user_permissions unique (user_id, permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (id, username, password, enabled) VALUES (1, 'admin', '123456', TRUE);

INSERT INTO roles (id, name) VALUES (1, 'sysadmin');
INSERT INTO roles (id, name) VALUES (2, 'project.user');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
