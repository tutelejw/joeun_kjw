#! /bin/sh

PARID="TOP"

/opt/ssbr/3rdparty/mariadb/bin/mysql -uroot -psystem ssbr -e "

update user_ssbr set use_select='0' where last_access_dt is null;

update post_dept set par_id='TOP' where dept_id='d000';

UPDATE post_dept a 
			, ( 
                SELECT a.* 
						FROM(
							SELECT dept_id
									, dept_name
									, dept_id as dept_id_full
									, dept_name as dept_name_full  
								FROM post_dept
							WHERE dept_id='${PARID}'
							union all		
							SELECT t2.dept_id
									, t2.dept_name
									, concat(t1.dept_id,'>',t2.dept_id) as dept_id_full
									, concat(t1.dept_name,'>',t2.dept_name)as dept_name_full  
								FROM post_dept AS t1
								LEFT JOIN post_dept AS t2 
								ON t2.par_id = t1.dept_id
							WHERE t1.dept_id='${PARID}'
							union all
							SELECT  t3.dept_id as dept_id
								,t3.dept_name
								,concat(t1.dept_id,'>',t2.dept_id,'>',t3.dept_id) as dept_id_full
								,concat(t1.dept_name,'>',t2.dept_name,'>',t3.dept_name) as dept_name_full   
								FROM post_dept AS t1
								LEFT JOIN post_dept AS t2 ON t2.par_id = t1.dept_id
								LEFT JOIN post_dept AS t3 ON t3.par_id = t2.dept_id
								LEFT JOIN post_dept AS t4 ON t4.par_id = t3.dept_id
							WHERE t1.dept_id = '${PARID}'
							GROUP BY t3.dept_id
							UNION ALL
							SELECT t4.dept_id 
								,t4.dept_name
								,concat(t1.dept_id,'>',t2.dept_id,'>',t3.dept_id,'>',t4.dept_id) as dept_id_full
								,concat(t1.dept_name,'>',t2.dept_name,'>',t3.dept_name,'>',t4.dept_name) as dept_name_full  
								FROM post_dept AS t1
								LEFT JOIN post_dept AS t2 ON t2.par_id = t1.dept_id
								LEFT JOIN post_dept AS t3 ON t3.par_id = t2.dept_id
								LEFT JOIN post_dept AS t4 ON t4.par_id = t3.dept_id
								LEFT JOIN post_dept AS t5 ON t5.par_id = t4.dept_id
							WHERE t1.dept_id = '${PARID}'
							GROUP BY t4.dept_id
							UNION ALL
							SELECT t5.dept_id 
								,t5.dept_name
								,concat(t1.dept_id,'>',t2.dept_id,'>',t3.dept_id,'>',t4.dept_id,'>',t5.dept_id) as dept_id_full
								,concat(t1.dept_name,'>',t2.dept_name,'>',t3.dept_name,'>',t4.dept_name,'>',t5.dept_name) as dept_name_full   
								FROM post_dept AS t1
								LEFT JOIN post_dept AS t2 ON t2.par_id = t1.dept_id
								LEFT JOIN post_dept AS t3 ON t3.par_id = t2.dept_id
								LEFT JOIN post_dept AS t4 ON t4.par_id = t3.dept_id
								LEFT JOIN post_dept AS t5 ON t5.par_id = t4.dept_id
								LEFT JOIN post_dept AS t6 ON t6.par_id = t5.dept_id
							WHERE t1.dept_id = '${PARID}'
							GROUP BY t5.dept_id
							UNION ALL
							SELECT t6.dept_id 
								,t6.dept_name
								,concat(t1.dept_id,'>',t2.dept_id,'>',t3.dept_id,'>',t4.dept_id,'>',t5.dept_id,'>',t6.dept_id) as dept_id_full
								,concat(t1.dept_name,'>',t2.dept_name,'>',t3.dept_name,'>',t4.dept_name,'>',t5.dept_name,'>',t6.dept_name) as dept_name_full 
								FROM post_dept AS t1
								LEFT JOIN post_dept AS t2 ON t2.par_id = t1.dept_id
								LEFT JOIN post_dept AS t3 ON t3.par_id = t2.dept_id
								LEFT JOIN post_dept AS t4 ON t4.par_id = t3.dept_id
								LEFT JOIN post_dept AS t5 ON t5.par_id = t4.dept_id
								LEFT JOIN post_dept AS t6 ON t6.par_id = t5.dept_id
								LEFT JOIN post_dept AS t7 ON t7.par_id = t6.dept_id
							WHERE t1.dept_id = '${PARID}'
							GROUP BY t6.dept_id
							UNION ALL
							SELECT t7.dept_id 
								,t7.dept_name
								,concat(t1.dept_id,'>',t2.dept_id,'>',t3.dept_id,'>',t4.dept_id,'>',t5.dept_id,'>',t6.dept_id,'>',t7.dept_id) as dept_id_full
								,concat(t1.dept_name,'>',t2.dept_name,'>',t3.dept_name,'>',t4.dept_name,'>',t5.dept_name,'>',t6.dept_name,'>',t7.dept_name) as dept_name_full 
								FROM post_dept AS t1
								LEFT JOIN post_dept AS t2 ON t2.par_id = t1.dept_id
								LEFT JOIN post_dept AS t3 ON t3.par_id = t2.dept_id
								LEFT JOIN post_dept AS t4 ON t4.par_id = t3.dept_id
								LEFT JOIN post_dept AS t5 ON t5.par_id = t4.dept_id
								LEFT JOIN post_dept AS t6 ON t6.par_id = t5.dept_id
								LEFT JOIN post_dept AS t7 ON t7.par_id = t6.dept_id
								LEFT JOIN post_dept AS t8 ON t7.par_id = t7.dept_id
							WHERE t1.dept_id = '${PARID}'
							GROUP BY t7.dept_id
						) a
				WHERE a.dept_id is not null
			) b
		SET a.dept_id_full = b.dept_id_full, a.dept_name_full=b.dept_name_full
		WHERE a.dept_id = b.dept_id
		;
"
