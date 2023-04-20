alter table user_ratings
    add check ( value >= -1 and value <= 1 );

create or replace function trigger_user_ratings_up() returns trigger
    language plpgsql
as
'
    begin
        new.evaluation_time = now();
        if (new.value IS NULL) then
            new.value = 0;
        end if;
        return new;
    end;
';

create trigger trigger_user_ratings_up_before
    before update
    on user_ratings
    for each row
execute procedure trigger_user_ratings_up();
