#!/bin/bash

cd mysqld_exporter

# 첫 번째 mysqld_exporter를 background-mode로 실행
nohup ./mysqld_exporter --config.my-cnf='.my.cnf1' --web.listen-address=:30000 &

# 두 번째 mysqld_exporter를 background-mode로 실행
nohup ./mysqld_exporter --config.my-cnf='.my.cnf2' --web.listen-address=:30001 &
