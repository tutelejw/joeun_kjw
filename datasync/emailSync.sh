#! /bin/sh

#DAY_HOUR=`date '+%Y%m%d%H'`

#echo $DAY_HOUR;

/opt/ssbr/3rdparty/mariadb/bin/mysql -uroot -psystem ssbr -e "

		LOAD DATA LOCAL INFILE '/home/adcsv/mail_insa.csv'
		REPLACE INTO TABLE user_mail
		FIELDS TERMINATED by ','
		LINES TERMINATED BY '\r\n'
		IGNORE 1 ROWS
		(user_id,user_mail)
		;
"

sleep 2

/opt/ssbr/3rdparty/mariadb/bin/mysql -uroot -psystem ssbr -e "

		update user_mail A join user_ssbr B on A.user_id=B.user_id
		set B.user_mail = A.user_mail

;
"
