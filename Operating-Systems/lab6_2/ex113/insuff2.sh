#!/bin/bash
while read nome2 cognome2 matricola2 voto2; do
	if (( voto2 < 18 )); then
		PRESENTE=0
		while read nome1 cognome1 matricola1 voto1; do
			if [[ "${matricola2}" == "${matricola1}" ]]; then
				PRESENTE=1
				break
			fi
		done < RisultatiProvaPratica1.txt
		if (( PRESENTE == 0 )); then
			echo "${matricola2} ${nome2} ${cognome2} ${voto2}"
		fi
	fi
done < RisultatiProvaPratica2.txt | sort -k 3
