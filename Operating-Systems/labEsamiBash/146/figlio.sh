#!/bin/bash
echo "$$"
i=${i}+1
if (( i > 10 )); then
	exit 0
fi
source ./figlio.sh
