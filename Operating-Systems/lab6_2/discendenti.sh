#!/bin/bash
if (( $# != 1 )); then echo "Passare un argomento intero"; exit 1; fi
if (( $1 > 0 )); then
	n=$1
	for (( I=$n; $I>0; I-- )); do
		./discendenti.sh $(( ${n}-1 )) &
	done
	wait
	echo "${n}"
	exit 0
elif (( $1 == 0 )); then echo $1; exit 0; fi
