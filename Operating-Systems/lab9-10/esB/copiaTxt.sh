#!/bin/bash

if [[ ! -d "$1" || ! -d "$2" ]]; then echo "passare due directory"; exit 1; fi

dir="$1"
dir2="$2"

for name in "${dir}"/*.txt; do
	if [[ -s "${name}" ]]; then
		cp -v "${name}" "${dir2}"
	fi
done
