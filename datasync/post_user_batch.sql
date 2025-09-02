set names utf8;
delete from post_user where comnl_reg_flag=0;

insert into post_user(user_id, name, dept_id, dept_name, duty_name, seq, updt_dt, use_flag)
		(
		select user_id, user_name, user_dept, user_dept_name, user_dept_rank, user_no, now(), '1'
		from user_ssbr
		where user_dept_rank is not null
		)ON DUPLICATE KEY UPDATE comnl_reg_flag=0, updt_dt=now();
