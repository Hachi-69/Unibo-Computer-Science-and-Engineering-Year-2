#!/bin/bash

if [[ ! -r $1 ]]; then echo "passare un percorso leggibile"; exit 1; fi

exec {FD}< $1

inverso=""
tmp=""

while read -u ${FD} riga; do
	tmp="${inverso}"
	inverso="${riga} 
${tmp}"

done

echo "${inverso}"
