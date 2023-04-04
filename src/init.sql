--초기화
drop table user_channel_relation;
drop table chat;
drop table channel;
drop table users;
drop table class;
drop sequence class_id_pk;
drop sequence channel_id_pk;
--시퀀스
create sequence class_id_pk;
create sequence channel_id_pk;

create table class(
    class_id number(10) primary key,
    class_name varchar(50)
);
create table users(
    user_id varchar(20) primary key,
    user_pw varchar(50),
    user_name varchar(50),
    Role varchar(2),
    class_id references class(class_id)
);

create table channel(
    channel_id number(10) primary key,
    channel_name varchar(50),
    class_id references class(class_id)
);

create table user_channel_relation(
    user_id references users(user_id),
    channel_id references channel(channel_id),
    primary key(user_id, channel_id)
);

create table chat(
    user_id references users(user_id),
    channel_id references channel(channel_id),
    created_at date,
    title varchar(50),
    content varchar(200),
    primary key(user_id, channel_id, created_at)
);


insert into class values (class_id_pk.nextval,'국민은행 2기');
insert into class values (class_id_pk.nextval,'롯데정보통신');

insert into users values ('adoo24','1234','준서','U',1);
insert into users values ('ooda24','1234','서준','U',1);
insert into users values ('oodaa24','1234','서서준','U',1);

insert into channel values (channel_id_pk.nextval,'공개채널',1);
insert into channel values (channel_id_pk.nextval, '준서, 서준 개인메세지',1);

--공개 채널(채널 id 1)
insert into user_channel_relation values ('adoo24',1);
insert into user_channel_relation values ('ooda24',1);
insert into user_channel_relation values ('oodaa24',1);
--개인 메세지 채널(채널 id 2)
insert into user_channel_relation values ('adoo24',2);
insert into user_channel_relation values ('ooda24',2);

--공개방채팅
insert into chat values ('adoo24',1,sysdate,'오늘의 숙제','숙제 내용입니다');
--개인채팅
insert into chat values ('adoo24',2,sysdate,'하이','하이');
insert into chat values ('ooda24',2,sysdate,'hi','hi');





select * from class;
select * from users;
select * from channel;
select * from user_channel_relation;
select * from chat;