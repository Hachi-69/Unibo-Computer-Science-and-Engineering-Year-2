#!/bin/bash
rigaPrec=""
while read riga; do
	output=$( echo "${riga}" | grep 'OPERATIVI' )
	if [[ -n "${output}" ]]; then
		echo "${rigaPrec%% *}"
	fi
	rigaPrec="${riga}"
done < lista.txt
