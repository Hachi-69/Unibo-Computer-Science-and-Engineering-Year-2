#!/bin/bash

while (( 1 )); do
	read p s t q
	if (( $? == 0 )); then
		echo "${q} ${t}"
	else
		break
	fi
done
