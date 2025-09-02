set names utf8;
#delete from user_ssbr where user_no !='' and datediff(now(), upt_dt)>1;
#truncate post_dept;
insert into post_dept (dept_id,dept_name,par_id,seq,updt_dt,rep_profile) values('011101000','국무총리','','0',now(),'1')
on duplicate key update updt_dt = now()
;
update post_dept set par_id='TOP' where dept_id='d000';
update user_ssbr set use_select=0 where datediff(now(),last_access_dt)>90;
