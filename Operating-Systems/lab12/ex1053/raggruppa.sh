#!/bin/bash

while read anno loc motivo danni || [ -n "${motivo}" ]; do
	num=`grep "${motivo}" cadutevic.txt | wc -l`
	echo "${motivo} ${num}"
done < cadutevic.txt | sort | uniq
