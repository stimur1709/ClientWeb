alter table users add check ( char_length(password) >= 5 and char_length(password) <= 255 );
alter table users add check ( char_length(username) >= 2 and char_length(username) <= 255 );
alter table users add check ( char_length(firstname) >= 2 and char_length(firstname) <= 255 );
alter table users add check ( char_length(lastname) >= 2 and char_length(lastname) <= 255 );
alter table author add check ( char_length(name) >= 2 and char_length(name) <= 255 );
alter table item add check ( char_length(title) >= 2 and char_length(title) <= 255 );
alter table user_contact add check ( char_length(contact) >= 2 and char_length(contact) <= 255 );
alter table user_contact add check ( char_length(code) = 7 );