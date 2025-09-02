#! /bin/sh

/opt/ssbr/3rdparty/mariadb/bin/mysql -u root -psystem ssbr -e "

update user_ssbr set use_select =0 where datediff(now(),last_access_dt)>90;


"

echo 'use_select success'
