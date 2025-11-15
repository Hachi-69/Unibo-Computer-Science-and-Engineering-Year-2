#!/bin/bash
denID=0
while read processIDv verdettoDex; do
[[ -n ${processIDv} && -n ${verdettoDex} ]]
	while read denIDp processIDp; do
	[[ -n ${denIDp} && -n ${processIDp} ]]
		if (( ${processIDv} == ${processIDp} )); then
			denID=${denIDp}
		fi
        done < processi.txt
	while read  nome cognome denIDd reatoDex; do
	[[ -n ${nome} && -n ${cognome} && -n ${denIDd} && -n ${reatoDex} ]]
		if (( denID == denIDd )); then
			echo "nome:${nome} cognome:${cognome} reatoDex:${reatoDex} verdettoDex:${verdettoDex}"
		fi
	done < denuncie.txt
done < verdetti.txt
