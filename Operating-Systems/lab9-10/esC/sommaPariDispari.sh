#!/bin/bash

i=1
sommaPari=0
sommaDispari=0

while read numero || [ -n "${numero}" ]; do
	if (( i % 2 == 0 )); then
		(( sommaPari+=${numero} ))
	else
		(( sommaDispari+=${numero} ))
	fi
	(( i++ ))
done < numeri.txt

echo "Pari: ${sommaPari} Dispari ${sommaDispari}"
