#!/bin/bash

if read LINEA ; then
  ./resto.sh
  echo "${LINEA}"
fi
