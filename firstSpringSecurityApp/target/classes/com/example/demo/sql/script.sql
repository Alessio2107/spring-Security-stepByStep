create table 'users'(
	'id' int not null auto_increment,
	'username' varchar(45) not null,
	'password' varchar(45) not null,
	'enabled' int not null,
	primarykey('id')
);

create table 'authorities'(
	'id' int not null auto_increment,
	'username' varchar(45) not null,
	'authority' varchar(45) not null,
	primary key ('id')
	
);


