#!/bin/bash

IFS=$'\t\n'

while read primo secondo terzo quarto; do
	echo "${terzo}"
done < cadutevic.txt

IFS=$' \t\n'
