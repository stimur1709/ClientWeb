create or replace function trigger_user_up() returns trigger
    language plpgsql
as
'
    begin
        if (new.login_time IS NULL) then
            new.login_time = old.login_time;
        end if;
        if (new.reg_time IS NULL) then
            new.reg_time = old.reg_time;
        end if;
        if (new.username IS not NULL) then
            new.username = old.username;
        end if;
        return new;
    end;
';

create trigger trigger_user_up_before
    before update
    on users
    for each row
execute procedure trigger_user_up();

