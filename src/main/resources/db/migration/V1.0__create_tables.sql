CREATE TABLE users
(
    id              BIGINT AUTO_INCREMENT   NOT NULL,
    email           VARCHAR(255)            NOT NULL,
    password_hash   VARCHAR(255)            NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    id              BIGINT AUTO_INCREMENT   NOT NULL,
    user_id         INT                     NOT NULL,
    status          INTEGER                 NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE tasks ADD CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;