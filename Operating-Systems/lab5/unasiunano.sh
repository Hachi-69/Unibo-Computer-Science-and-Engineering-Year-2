#!/bin/bash
LEGGI=0
while read RIGA; do

	if (( ${LEGGI} == 0 )); then 
		echo "${RIGA}"; LEGGI=1
	else
		LEGGI=0
	fi
done
