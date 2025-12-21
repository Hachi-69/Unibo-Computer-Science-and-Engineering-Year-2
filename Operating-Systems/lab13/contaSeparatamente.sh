#!/bin/bash

if (( $# == 0 )); then echo "passare almeno un argomento"; exit 1; fi
if (( $# > 9 )); then echo "passare al massimo 9 argomenti"; exit 1; fi

num=$#
rigeDis=0
rigePar=0

for (( i=1; i<=$num; i++ )); do
	if [[ ! -r "${!i}" ]]; then continue; fi

	righeFile=`wc -l < "${!i}"`

	if (( i % 2 == 0 )); then
		(( rigePar+=righeFile ))
	else
		(( rigeDis+=righeFile ))
	fi
done

echo "$rigePar"
echo "$rigeDis" 1>&2
