use high_school;

delimiter //
create procedure students_teacher_per_course
(in given_course integer)
begin

	if given_course in (select id_course from Course) then 

		select c.name as course_name, concat(t.first_name,", ",t.last_name) as teacher_name
		from Course as c join Teacher as t on c.id_teacher = t.id_teacher
		where c.id_course = given_course;

		select concat(s.last_name,", ",s.first_name) as Stundets
		from Course as c join Assist as a on c.id_course = a.id_course
			join Student as s on s.id_student = a.id_student
		where c.id_course = given_course
		order by s.last_name;
	
    else
    
		select "Nonexistent Course." as result;

	end if;

end//
delimiter ;

call students_teacher_per_course(1);