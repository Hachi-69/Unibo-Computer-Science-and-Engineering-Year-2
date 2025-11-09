#!/bin/bash
for char in {c..g}; do

	for name in /usr/include/?${char}*; do

		if [ ${#name} -lt 18 -o ${#name} -gt 23 ]; then
			echo ${name}
		fi

	done

done
