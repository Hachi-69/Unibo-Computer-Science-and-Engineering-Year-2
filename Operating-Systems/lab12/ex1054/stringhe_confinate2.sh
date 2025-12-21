#!/bin/bash

IFS=$'\t\n'

sed 's/" "/"\t"/g' cadutevic2.txt | while read primo secondo terzo quarto; do
	echo "${terzo}"
done

IFS=$' \t\n'
