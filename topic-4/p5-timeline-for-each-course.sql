use high_school;

delimiter //
create procedure timeline_for_each_course
(in given_teacher integer)
begin

    if given_teacher in (select id_teacher from Teacher) then

        select concat(t.last_name,", ",t.first_name) as teacher
        from Teacher as t
        where t.id_teacher = given_teacher;

        select concat(case 
                        when s.day_of_week = 0 then "Sunday"
                        when s.day_of_week = 1 then "Monday"
                        when s.day_of_week = 2 then "Tuesday"
                        when s.day_of_week = 3 then "Wednesday"
                        when s.day_of_week = 4 then "Thursday"
                        when s.day_of_week = 5 then "Friday"
                        when s.day_of_week = 6 then "Saturday"
                        else "Wrong day"
                      end," ",s.time_from," - ",s.time_to,": ",c.name) as schedule
        from Teacher as t join Course as c on t.id_teacher = c.id_teacher
            join Schedule as s on s.id_course = c.id_course;

    else

        select "Nonexistent Teacher." as result;

    end if;

end //
delimiter ;