#!/bin/bash
if (( $# != 1 )); then echo "give just one argument"; exit 1; fi
if [[ ! -r $1 ]]; then echo "Not readable"; exit 2; fi
OUT=""
while read FIRST SECOND OTHER ; do
	if [[ -n ${SECOND} ]]; then
		OUT=${OUT}${SECOND}
	fi
done < $1
echo "OUT=${OUT}"
