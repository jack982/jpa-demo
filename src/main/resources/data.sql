insert into process(id,name) values (10001, 'process 1');
insert into process(id,name) values (10002, 'process 2');
insert into process(id,name) values (10003, 'process 3');

insert into user(id, username) values(1, 'john');
insert into user(id, username) values(2, 'anna');

insert into tag(id, tag, user_id) values (1001, 'foo', 1);
insert into tag(id, tag, user_id) values (1002, 'moo', 1);
insert into tag(id, tag, user_id) values (1003, 'test', 2);

insert into process_tag(process_id, tag_id) values ( 10001, 1001);
insert into process_tag(process_id, tag_id) values ( 10001, 1003);

insert into process_tag(process_id, tag_id) values ( 10002, 1002);

insert into process_tag(process_id, tag_id) values ( 10003, 1001);
insert into process_tag(process_id, tag_id) values ( 10003, 1002);
insert into process_tag(process_id, tag_id) values ( 10003, 1003);
