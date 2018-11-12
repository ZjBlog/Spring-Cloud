CREATE TABLE user (
  id varchar (32) not  null primary key,
  user_name VARCHAR(50) NOT NULL,
  email VARCHAR(50),
  password VARCHAR(500),
  activated BOOLEAN DEFAULT FALSE,
  activation_key VARCHAR(50) DEFAULT NULL,
  reset_password_key VARCHAR(50) DEFAULT NULL
);