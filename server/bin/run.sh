#!/bin/sh
#running the process cwmd and then starting loginapp
#timerd dbmgr base cell 
echo "[running process cwmd......]"
./sync_db ./cfg.ini
./cwmd ./cfg.ini &
./loginapp ./cfg.ini 1 ./log/loginlog_1 &
./dbmgr ./cfg.ini 3 ./log/dblog_3 &
./timerd ./cfg.ini 4 ./log/timerd_4 &
./logapp ./cfg.ini 5 ./log/logapplog_5 &
./baseapp ./cfg.ini 12 ./log/baselog_6 &
./cellapp ./cfg.ini 13 ./log/celllog_7 &



