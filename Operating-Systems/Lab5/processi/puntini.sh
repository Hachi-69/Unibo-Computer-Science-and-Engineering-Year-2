#!/bin/bash
if (( $# != 1 )); then echo "Passare solo un argomento"; exit 1; fi
if [[ ! $1 =~ ^[0-9]+$ ]]; then echo "Passare un numero intero positivo"; exit 3; fi
SEC=$1
I=0
while (( ${I} < ${SEC} )); do
	sleep 1
	echo -n " . ${BASHPID} "
	(( I=${I}+1 ))
done
