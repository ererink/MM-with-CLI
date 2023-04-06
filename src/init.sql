--초기화
drop table user_channel_relation;
drop table chat;
drop table channel;
drop table users;
drop table class;
drop sequence class_id_pk;
drop sequence channel_id_pk;
drop sequence chat_id_pk;

--시퀀스
create sequence class_id_pk start with 1;
create sequence channel_id_pk start with 1;
create sequence chat_id_pk start with 1;

--테이블
create table class(
    class_id number(10) primary key,
    class_name varchar2(50)
);
create table users(
    user_id varchar2(20) primary key,
    user_pw varchar2(50),
    user_name varchar2(50),
    Role varchar(2),
    class_id references class(class_id) on delete cascade
);

create table channel(
    channel_id number(10) primary key,
    channel_name varchar2(50),
    class_id references class(class_id) on delete cascade,
    isOpen number(1)
);

create table user_channel_relation(
    user_id references users(user_id) on delete cascade,
    channel_id references channel(channel_id) on delete cascade,
    primary key(user_id, channel_id)
);

create table chat(
    chat_id varchar2(50) primary key,
    user_id references users(user_id) on delete cascade,
    channel_id references channel(channel_id) on delete cascade,
    created_at date default sysdate,
    title varchar2(50),
    content varchar2(200)
);


--class data
insert into class values (class_id_pk.nextval , '국민은행 2기 1반');
insert into class values (class_id_pk.nextval , '국민은행 2기 2반');
insert into class values (class_id_pk.nextval,'국민은행 2기 3반');
insert into class values (class_id_pk.nextval,'신한은행 1기 A');
insert into class values (class_id_pk.nextval,'신한은행 1기 B');
insert into class values (class_id_pk.nextval,'신한은행 1기 C');

select * from class;

-- users data
insert into users(user_id, user_pw, user_name, Role) values ('admin','1234','관리자','A');

insert into users values ('adoo24','1234','김준서','U',2);
insert into users values ('yelkim02','1234','김예린','U',2);
insert into users values ('ruisya330','1234','이경원','U',2);
insert into users values ('joy1660','1234','조아영','U',2);

insert into users values ('youou120','1234','유재석','U',3);
insert into users values ('ongong','1234','조세호','U',3);
insert into users values ('nimoj00','1234','정준하','U',4);
insert into users values ('donn11u','1234','정형돈','U',4);

insert into users values ('hohoya','1234','강호동','U',5);
insert into users values ('songc','1234','은지원','U',5);
insert into users values ('pyoblockb','1234','표지훈','U',6);
insert into users values ('ohnim','1234','송민호','U',6);

select * from users;

--channel data
insert into channel values (channel_id_pk.nextval,'공개채널',2,1);
insert into channel values (channel_id_pk.nextval,'공개채널',3,1);
insert into channel values (channel_id_pk.nextval,'공개채널',4,1);
insert into channel values (channel_id_pk.nextval,'공개채널',5,1);
insert into channel values (channel_id_pk.nextval,'공개채널',6,1);

insert into channel values (channel_id_pk.nextval, '2반 4조 개인메세지',2,0);
insert into channel values (channel_id_pk.nextval, '예린, 아영 개인메세지',2,0);
insert into channel values (channel_id_pk.nextval, '경원, 준서 개인메세지',2,0);

insert into channel values (channel_id_pk.nextval, '재석 세호 개인메세지',3,0);

insert into channel values (channel_id_pk.nextval, '준하랑 형돈이',4,0);
insert into channel values (channel_id_pk.nextval, '신서유기 old팀',5,0);
insert into channel values (channel_id_pk.nextval, '한림예고인',6,0);

select * from channel;

--개인 메세지 채널 data
insert into user_channel_relation values ('adoo24',5);
insert into user_channel_relation values ('yelkim02',5);
insert into user_channel_relation values ('ruisya330',5);
insert into user_channel_relation values ('joy1660',5);

insert into user_channel_relation values ('yelkim02',6);
insert into user_channel_relation values ('joy1660',6);

insert into user_channel_relation values ('ruisya330',7);
insert into user_channel_relation values ('adoo24',7);

insert into user_channel_relation values ('youou120',8);
insert into user_channel_relation values ('ongong',8);

insert into user_channel_relation values ('nimoj00',9);
insert into user_channel_relation values ('donn11u',9);

insert into user_channel_relation values ('hohoya',10);
insert into user_channel_relation values ('songc',10);
insert into user_channel_relation values ('pyoblockb',11);
insert into user_channel_relation values ('ohnim',11);

--개인채팅 data
insert into chat values (chat_id_pk.nextval,'adoo24',2,sysdate,'불사조팀','하이');
insert into chat values (chat_id_pk.nextval,'yelkim02',2,sysdate,'아영이에게','안녕 난 예린이야');
insert into chat values (chat_id_pk.nextval,'ruisya330',2,sysdate,'경원준서방','뭐해? 나 발표중');
insert into chat values (chat_id_pk.nextval,'youou120',3,sysdate,'유퀴즈?','오늘의 퀴즈는?');
insert into chat values (chat_id_pk.nextval,'nimoj00',4,sysdate,'무한도전','무도 멤버 모이자');
insert into chat values (chat_id_pk.nextval,'hohoya',5,sysdate,'올드보이채팅','신서유기 커밍쑤운');
insert into chat values (chat_id_pk.nextval,'nimoj00',6,sysdate,'한림예고','3시까지 녹음실로 와');

--공개방 채널 채팅 data
insert into chat values (chat_id_pk.nextval,'admin',2,sysdate,'프로젝트 공지','JDBC MiniPJ');
insert into chat values (chat_id_pk.nextval,'admin',2,sysdate,'KB 과제 공지','DB_WS_04 9시까지');
insert into chat values (chat_id_pk.nextval,'admin',2,sysdate,'수업자료','JDBC.pdf');

insert into chat values (chat_id_pk.nextval,'admin',3,sysdate,'KB 과제 공지','DB_WS_04 9시까지');
insert into chat values (chat_id_pk.nextval,'admin',4,sysdate,'신한 공지','SH 과제 제출 공지');
insert into chat values (chat_id_pk.nextval,'admin',5,sysdate,'신한 공지','SH 과제 제출 공지');


-----------------------------------------

select * from class;
select * from users;
select * from channel;
select * from user_channel_relation;
select * from chat;
select * from channel where class_id = 1 or isOpen = 1;

select * from channel  join user_channel_relation  using (channel_id) where (user_id = 'adoo24' or isopen=1) and class_id = 1;

commit;