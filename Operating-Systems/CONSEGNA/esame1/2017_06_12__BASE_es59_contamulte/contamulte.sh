#!/bin/bash

precMulta=""
i=0

while read nome cognome multa giorno mese anno; do
	if [[ "${precMulta}" == ""  ]]; then
		precMulta="${multa}"
	fi

	if [[ "${multa}" == "${precMulta}" ]]; then
		(( i=${i}+1 ))
	else
		echo "${precMulta} ${i}"
		precMulta=${multa}
		i=1
	fi
done
