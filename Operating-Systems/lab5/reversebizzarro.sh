#!/bin/bash
STR=$1
OUT=""
for (( i=${#STR}; i>0; i=i-1 )); do
	TMP=$( echo ${STR} | cut -b ${i} )
	OUT=${OUT}${TMP}
done
echo "${OUT}"
