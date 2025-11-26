#!/bin/bash
while read LINEA ; do
	PAROLA="${LINEA%% *}"
	echo "${PAROLA}" 1>&2
done
