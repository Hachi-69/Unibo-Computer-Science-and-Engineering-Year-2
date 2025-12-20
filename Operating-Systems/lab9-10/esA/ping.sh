#!/bin/bash

rm ping.txt
rm pong.txt

./pong.sh &

touch ping.txt

for (( i=0; i<10; i++ )); do
	if [[ -r ping.txt ]]; then
		echo "ping"
		rm ping.txt
		touch pong.txt
	fi
	sleep 2
done
