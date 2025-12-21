#!/bin/bash

if [[ ! -r "$1" ]]; then echo "passare un percorso di un file .CSV"; exit 1; fi
if (( $2 < 1 )); then echo "passare un indice valido"; exit 1; fi

cut -d "," -f $2 "$1"
