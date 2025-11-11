#!/bin/bash
exec {FD}< /usr/include/stdio.h
if (( $? == 0 )); then
	NUM=0
	while read -u ${FD} -N 1 -r A; do
		((NUM=${NUM}+1))
	done
	exec {FD}>&-
	echo ${NUM}
fi
