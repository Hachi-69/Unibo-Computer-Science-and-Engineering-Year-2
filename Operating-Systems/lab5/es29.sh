#!/bin/bash
cut -b 3-5,10-15 es28.txt | sed 's/ //g' | sed 's/*//g' | sed 's/\t//g' | wc -c
