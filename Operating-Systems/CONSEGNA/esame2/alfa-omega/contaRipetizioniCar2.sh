#!/bin/bash

if [[ -z $1 ]]; then echo "Passare una stringa"; exit 1; fi

str="$1"
counter=0

for char in {a..z}; do
	for word in "${str}"; do
		for (( i=0; i<${#str}; i++ )); do
			if [[ "${str:i:1}" == "${char}" ]]; then
				(( counter=${counter}+1 ))
			fi
		done
		if (( counter != 0 )); then
			echo "${char} ${counter}"
		fi
		counter=0
	done
done
