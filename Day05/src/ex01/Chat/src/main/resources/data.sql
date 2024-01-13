insert into chat_schema.chat_user (login, password) values
('Dil', '1234567'),
('Manar', '2345678'),
('Tema', '3456789'),
('Olya', '4567890'),
('Rus', '56789012');


insert into chat_schema.chat_room (name, owner) values
('fludilka', (select id from chat_schema.chat_user where login = 'Tema'))
,('aboba', (select id from chat_schema.chat_user where login = 'Rus'))
,('tennis', (select id from chat_schema.chat_user where login = 'Dil'))
,('derevyashki', (select id from chat_schema.chat_user where login = 'Olya'))
,('general', (select id from chat_schema.chat_user where login = 'Manar'))
,('find_job', (select id from chat_schema.chat_user where login = 'Dil'))
;

insert into chat_schema.message(author, room, content, message_date_time) values
    (1, 3, 'Hi, when is tennis on?', '2023-12-21 19:00:12')
    ,(2, 6, 'I really love C++ but I can''t find a job' , '2023-12-21 21:10:02')
    ,(4, 5, 'obame', '2023-12-21 19:30:44')
    ,(3, 3, 'let''s go tennis?', '2023-12-22 10:30:42')
    ,(5, 1, 'Do you know how much eggs cost?', '2023-12-22 19:30:44')
;