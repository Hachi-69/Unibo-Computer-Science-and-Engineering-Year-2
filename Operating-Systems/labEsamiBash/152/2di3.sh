#!/bin/bash
exec {FD}< /usr/include/stdio.h
while read -u ${FD} prima seconda terza altro; do
	if [[ -n ${terza} ]]; then
		if [[ -n ${terza:1:1} ]]; then
			echo "${terza:1:1}"
		fi
	fi
done
