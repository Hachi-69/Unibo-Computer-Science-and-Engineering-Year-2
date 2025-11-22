#!/bin/bash
OUT=`./lanciaPrendiPID.sh`
echo "${OUT}"
for pid in ${OUT}; do
	kill -9 ${pid}
done
