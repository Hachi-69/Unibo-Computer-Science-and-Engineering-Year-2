#!/bin/bash

cat esame2.txt

while read mat voto; do
	if ` ! grep -q ${mat} esame2.txt `; then
		echo "${mat} ${voto}"
	fi
done < esame1.txt
