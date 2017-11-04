1、搭建环境
	1.1 导开发包
		mysql驱动
		c3p0连接池
		dbutils
		jstl开发包
	
	
	1.2 创建库和表
		create database day19;
		use day19;
		
		create table privilege
		(
			id varchar(40) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
		
		create table role
		(
			id varchar(40) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
		
		create table user
		(
			id varchar(40) primary key,
			username varchar(100) not null unique,
			password varchar(100) not null
		);
		
		create table role_privilege
		(
			role_id varchar(40),
			privilege_id varchar(40),
			primary key(role_id,privilege_id),
			constraint role_id_FK foreign key(role_id) references role(id),
			constraint privilege_id_FK foreign key(privilege_id) references privilege(id)
		);
		
		create table user_role
		(
			user_id varchar(40),
			role_id varchar(40),
			primary key(user_id,role_id),
			constraint user_id_FK foreign key(user_id) references user(id),
			constraint role_id_FK1 foreign key(role_id) references role(id)
		);