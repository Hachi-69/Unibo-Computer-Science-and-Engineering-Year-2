#!/bin/bash
residuo=${PATH}
while [[ -n ${residuo} ]]; do
	primoPercorso=${residuo%%:*}
	echo ${primoPercorso}
	precedenteResiduo=${residuo}
	residuo=${residuo#*:}
	if [[ ${precedenteResiduo} == ${residuo} ]]; then
		break
	fi
done
