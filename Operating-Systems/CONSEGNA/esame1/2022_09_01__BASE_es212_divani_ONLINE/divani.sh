#!/bin/bash

if (( $# != 2 )) ; then 
	echo "errore: servono 2 argomenti: larghezza_minima  altezza_massima"
	exit 1
fi

LARGMIN=$1
ALTMAX=$2

while read nome larghezza altezza profondita; do
	if (( ${larghezza} >= ${LARGMIN} && ${altezza} <= ${ALTMAX} )); then
		echo "${nome} ${larghezza} ${altezza} ${profondita}"
	fi
done < divani.txt
