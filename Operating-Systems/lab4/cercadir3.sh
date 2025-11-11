#!/bin/bash
for name in /usr/include/*; do

	if test -d ${name} -a -r ${name} -a ${name} -nt /usr/include/stdio.h ; then
		echo ${name}
	fi
done
