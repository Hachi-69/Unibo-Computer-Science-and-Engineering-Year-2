#!/bin/bash
LungNomiDirectory=0
FileNonDir=0
for file in `ls ./` ; do
	if [[ -d ${file} ]]; then
		(( LungNomiDirectory=${LungNomiDirectory}+${#file} ))
	else
		(( FileNonDir=${FileNonDir}+1 ))
	fi
done

echo "file non dir = ${FileNonDir} lung nomi directory = ${LungNomiDirectory}"
