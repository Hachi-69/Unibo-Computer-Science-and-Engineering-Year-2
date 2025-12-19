#!/bin/bash

somma=0
numero=""

while read riga; do
	if [[ -n "${riga}" ]]; then
		echo -n  "${riga%%,*}, "
		echo "${riga##*,}"
		numero="${riga#*,}"
		numero="${numero%,*}"
		(( somma=${somma}+${numero} ))
	fi
done < input1.txt

echo "${somma}"
