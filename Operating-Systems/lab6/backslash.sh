#!/bin/bash
while read RIGA; do
	RIGA="${RIGA//\*/\\\*}"
	RIGA="${RIGA//\?/\\\?}"
	RIGA="${RIGA//\[/\\\[}"
	RIGA="${RIGA//\]/\\\]}"
	echo "${RIGA}"
done
