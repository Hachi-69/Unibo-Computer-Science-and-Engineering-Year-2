#!/bin/bash
newer=""
for file in `find /usr/include/linux -mindepth 2 -name "*.h"`; do
	if [[ -z ${newer} || ${file} -nt ${newer} ]]; then
		newer=${file}
	fi
done
echo "${newer}"

