create table wba_user(
 id INTEGER,
  login_id VARCHAR(50),
  nick_name VARCHAR(150),
  email_address VARCHAR(200),
  status VARCHAR(50),
  created_date DATETIME,
  status_changed_date DATETIME,
  modified_date DATETIME
);

create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

