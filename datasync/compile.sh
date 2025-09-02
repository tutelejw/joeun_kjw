#!/bin/sh

SCRIPT_NAME=$0
export PROJECT_HOME=/www/datasync
cd $PROJECT_HOME

export JAVA_HOME=/usr/java/jdk1.8.0_144
export JAVA_HOME_R=/opt/ssbr/3rdparty/jdk
export LANG=ko_KR.UTF-8

JAVAC_EXECUTABLE=javac
if [ -n "$JAVA_HOME" ]
then
    JAVAC_EXECUTABLE=$JAVA_HOME/bin/javac
fi

cd /usr/local
if [ ! -d ${JAVA_HOME_R} ]; then

    tar xvfz ${PROJECT_HOME}/ext/jdk-8u31-linux-x64.tar.gz
fi
if [ ! -d ${JAVA_HOME} ]; then
    ln -s jdk1.8.0_31 jdk1.8
fi

if [ -d ${PROJECT_HOME}/bin ]; then
    /bin/rm -rf ${PROJECT_HOME}/bin/*
else
    mkdir ${PROJECT_HOME}/bin
fi

PACKAGEPATH=com/sqisoft/ssbr/datasync
EXEC_CLASSPATH=".:${JAVA_HOME}/lib:${PROJECT_HOME}/bin:$JAVA_HOME/jre/lib/rt.jar"

LIB_DIR=${PROJECT_HOME}/lib
 
for jar in `find $LIB_DIR -name '*.jar'`
do
  EXEC_CLASSPATH=$EXEC_CLASSPATH:$jar
done

#echo "$JAVAC_EXECUTABLE -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin -sourcepath ${PROJECT_HOME}/src/${PACKAGEPATH}/"

$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/vo/*.java
$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/frame/sys/*.java
$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/util/*.java

$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/resources/*.java
/bin/cp -p ${PROJECT_HOME}/src/${PACKAGEPATH}/resources/*.properties ${PROJECT_HOME}/bin/${PACKAGEPATH}/resources/

$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/entitybc/dao/*.java
$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/entitybc/entity/*.java
$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/entitybc/*.java

$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/processbc/*.java
$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/processbc/csvreader/*.java

$JAVAC_EXECUTABLE -Xlint:unchecked -classpath $EXEC_CLASSPATH -d  ${PROJECT_HOME}/bin ${PROJECT_HOME}/src/${PACKAGEPATH}/action/*.java

/bin/cp -pr ${PROJECT_HOME}/src/conf ${PROJECT_HOME}/bin/conf
/bin/cp -p ${PROJECT_HOME}/src/log4j.xml ${PROJECT_HOME}/bin/log4j.xml

exit $result
