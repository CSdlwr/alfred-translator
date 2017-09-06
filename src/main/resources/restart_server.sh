#!/usr/bin/env bash
set -e
set -x

jar_file=$1.jar
jar_dir="/Users/lvluming/Library/Application Support/Alfred 3/Alfred.alfredpreferences/workflows/user.workflow.06C737D2-6DF5-45A6-9EE2-B862A44C8D79"
main_class="lvluming.Server"

echo "restarting server."
current_server_pid=$(ps -ef | grep "$main_class" | grep -v "grep" | awk '{print $2}')
echo "find current server pid [$current_server_pid]"
if [ $current_server_pid"x" != "x" ];then
    kill -9 $current_server_pid
    echo "current_server killed."
fi
echo "retarting server"
ls -l "$jar_dir"
ls -l "$jar_dir/$jar_file"
java -cp "$jar_dir/$jar_file" $main_class 1>>/Users/lvluming/logs/alfred-workflow/server.log 2>&1 &
echo "server restart success."
