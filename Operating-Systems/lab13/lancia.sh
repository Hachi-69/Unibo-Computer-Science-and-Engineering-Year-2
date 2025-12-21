#!/bin/bash

./contaSeparatamente.sh `ls -S1 /usr/include/*.h | head -n 7` 1>&2
