#!/bin/bash

echo ` find /usr/include/ -type f -name "*.h" -exec grep '*' '{}' \; | wc -l ` 1>&2
