use high_school;

delimiter //
create procedure percentage_of_students
(in given_course integer)
begin

    if given_course in (select id_course from Course) then

        select (count(n.final_note)/count(*)*100) as student_that_passed
        from Note as n
        where n.id_course = given_course
            and n.final_note >= 6;

        select (count(n.final_note)/count(*)*100) as student_that_failed
        from Note as n
        where n.id_course = given_course
            and n.final_note < 6;

    else

        select "Nonexistent Course." as result;

    end if;

end //
delimiter ;

call percentage_of_students(2);