#!/bin/bash
for (( i=0; i<10; i++ )); do
	mv "1.${i}" "2.$(( 9-i ))"
done
