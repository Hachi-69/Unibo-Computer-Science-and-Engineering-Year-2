#!/bin/bash
for char in {c..g}; do

	for name in /usr/include/?${char}*; do

		if [[ (${#name} < 18 || ${#name} > 23) ]]; then
			echo ${name}
		fi

	done

done
