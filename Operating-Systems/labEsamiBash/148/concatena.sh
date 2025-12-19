#!/bin/bash

i=0

if [[ -r "output.txt" ]]; then echo -n "" > output.txt; fi

for name in /usr/include/std* ; do
	exec {FD}< ${name}
	while read -u ${FD} riga; do
		if (( i > 0 && i < 4 )); then
			echo "${riga}" >> output.txt
		fi
		if (( i > 4 )); then break; fi
		(( i=${i}+1 ))
	done
	i=0
	exec {FD}>&-
done
