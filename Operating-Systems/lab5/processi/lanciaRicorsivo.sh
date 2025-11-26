#!/bin/bash
if (( $# != 1 )); then echo "Passare solo un argomento"; exit 1; fi
if [[ ! $1 =~ ^[1-9]+$ ]]; then echo "Passare un numero intero positivo"; exit 2; fi
./lanciaRicorsivo.sh $(( $1 - 1 )) &
echo $!
wait $!
