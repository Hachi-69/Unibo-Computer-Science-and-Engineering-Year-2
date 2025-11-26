#!/bin/bash
RANDOM=$(( `date+%s` % 32768 ))
NUM=0
while (( ${RANDOM}%10 != 2 )); do

	(( NUM=${NUM}+1 ))

done
echo "NUM=${NUM}"
