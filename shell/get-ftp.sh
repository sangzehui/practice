#!/bin/bash

# 拉取ftp文件，文件不存在或拉取失败10分钟后重试

function log(){
	msg=$1
	echo `date +'%Y-%m-%d-%H:%M:%S'`" "$msg
}

function main(){
	url=$1
	tmp_file=$(mktemp)
	while [ 1 -eq 1 ]
	do
		wget $url -O $tmp_file
		if [ $? -eq 0 ]
		then
			break
		fi
		log $url" is not ready"
		sleep 10m
	done
	# do something
	rm $tmp_file
}

main $*
