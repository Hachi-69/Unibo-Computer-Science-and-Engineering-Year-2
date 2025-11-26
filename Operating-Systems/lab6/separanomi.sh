#!/bin/bash
RESIDUO=${PATH}
while [[ -n ${RESIDUO} ]]; do
	PRIMOCHAR=${RESIDUO:0:1}
	if [[ ${PRIMOCHAR} == "/" || ${PRIMOCHAR} == ":" ]]; then
 		RESIDUO=${RESIDUO:1}
	else
 		PEZZO1=${RESIDUO%%:*}
		PEZZO2=${RESIDUO%%/*}
 	if (( ${#PEZZO1} < ${#PEZZO2} )); then
 		PEZZO=${PEZZO1}
 	else
 		PEZZO=${PEZZO2}
 	fi
 	echo ${PEZZO}
 	OFFSET=$(( ${#PEZZO}+1 ))
 	RESIDUO=${RESIDUO:${OFFSET}}
	fi
done
