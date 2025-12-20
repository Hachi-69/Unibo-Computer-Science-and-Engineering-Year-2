#!/bin/bash

for (( i=0; i<10; i++ )); do
	if [[ -r pong.txt ]]; then
		echo "pong"
		rm pong.txt
		touch ping.txt
	fi
	sleep 2
done
