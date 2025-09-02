#!/bin/sh

SCRIPT_NAME=$0
EXECUTABLE_CLASS=com.sqisoft.ssbr.datasync.action.DataSyncUserAction
BIN_DIR=`dirname $0`

DAY_HOUR=`date '+%Y%m%d%H'`
HOSTNAME=`hostname`
#LOG_FILE=${LOG_DIR}/${HOSTNAME}_${DAY_HOUR}.log
LOG_FILE=/data/log/datasync.log

export JAVA_HOME=/usr/java/jdk1.8.0_144
export JAVA_HOME_R=/opt/ssbr/3rdparty/jdk


export PATH=$JAVA_HOME/bin:$PATH
export EPSERVICEDIR=/www/datasync/bin/conf/
export CLASSPATH=$CLASSPATH:/www/datasync/lib

EXEC_CLASSPATH="."

DIST_DIR=$BIN_DIR/..
LIB_DIR=$BIN_DIR/lib

for jar in `find $LIB_DIR -name '*.jar'`
do
  EXEC_CLASSPATH=$EXEC_CLASSPATH:$jar
done

EXEC_CLASSPATH=$EXEC_CLASSPATH:$BIN_DIR/bin

JAVA_EXECUTABLE=java
if [ -n "$JAVA_HOME" ]
then
  JAVA_EXECUTABLE=$JAVA_HOME/bin/java
fi

CNT=`ps -ef | grep $JAVA_EXECUTABLE | grep datasync | grep -v grep | wc -l`

if [ $CNT -gt 0 ] ; then
	echo "already running process";
	echo "`ps -ef | grep $JAVA_EXECUTABLE | grep -v grep`";
	exit 0;
else
	$JAVA_EXECUTABLE -classpath $EXEC_CLASSPATH $EXECUTABLE_CLASS "$@" >> ${LOG_FILE} &
fi
echo "suceess";

sleep 2

#echo "root dept insert"
#mysql -u root -psystem ssbr < /www/datasync/root_dept.sql

#sleep 2
#
#echo "post_dept_user insert"
#mysql -u root -psystem ssbr < /www/datasync/post_user_batch.sql

sleep 2

echo "set dept_full_code"
sh /www/datasync/dept_full_code_sql.sh
