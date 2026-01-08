package a02a.e1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SpreadRowImpl implements SpreadRow {
    // Lista che contiene i calcolatori (o i valori fissi)
    private final List<Optional<Supplier<Integer>>> cells;

    // NUOVO: Set che tiene traccia di quali indici sono formule
    private final Set<Integer> formulaIndexes = new HashSet<>();

    public SpreadRowImpl(int size) {
        this.cells = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.cells.add(Optional.empty());
        }
    }

    @Override
    public int size() {
        return this.cells.size();
    }

    @Override
    public boolean isFormula(int index) {
        // È una formula se l'abbiamo segnato nel set
        return formulaIndexes.contains(index);
    }

    @Override
    public boolean isNumber(int index) {
        // È un numero se NON è vuoto e NON è una formula
        return !isEmpty(index) && !isFormula(index);
    }

    @Override
    public boolean isEmpty(int index) {
        // Controlliamo se la cella è Optional.empty()
        return cells.get(index).isEmpty();
    }

    @Override
    public List<Optional<Integer>> computeValues() {
        // Scorre la lista interna 'cells'.
        // Per ogni elemento (che è un Optional<Supplier<Integer>>):
        // 1. Se è vuoto -> resta vuoto.
        // 2. Se c'è un Supplier -> chiama .get() per eseguire il calcolo.
        return cells.stream()
                .map(opt -> opt.map(Supplier::get))
                .collect(Collectors.toList());
    }

    @Override
    public void putNumber(int index, int number) {
        // 1. Inseriamo un Supplier che restituisce sempre il numero fisso
        cells.set(index, Optional.of(() -> number));

        // 2. IMPORTANTE: Rimuoviamo l'indice dal set delle formule (è un numero puro)
        formulaIndexes.remove(index);
    }

    @Override
    public void putSumOfTwoFormula(int resultIndex, int index1, int index2) {
        // 1. Inseriamo la logica: "quando ti chiamo, prendi i valori attuali di i1 e i2
        // e sommali"
        // Nota: usiamo "this" per riferirci ai valori dinamici della riga stessa
        cells.set(resultIndex, Optional.of(() -> getVal(index1) + getVal(index2)));

        // 2. Segniamo che questa è una formula
        formulaIndexes.add(resultIndex);
    }

    // Helper privato per leggere il valore di una cella in modo sicuro (per le
    // formule)
    private int getVal(int index) {
        // Se la cella è vuota o non esiste, le formule di solito considerano 0 o
        // lanciano eccezione.
        // Guardando i test, sembra assumere che i valori esistano.
        // Usiamo .flatMap per scompattare l'Optional e chiamare il Supplier
        return cells.get(index).map(Supplier::get).orElse(0);
    }

    @Override
    public void putMultiplyElementsFormula(int resultIndex, Set<Integer> indexes) {
        // Inseriamo un Supplier che, quando chiamato:
        cells.set(resultIndex, Optional.of(() -> indexes.stream()
                .mapToInt(this::getVal) // 1. Prende il valore di ogni cella indicata
                .reduce(1, (a, b) -> a * b) // 2. Li moltiplica tutti tra loro (partendo da 1)
        ));

        // Ricordiamoci di segnare che questa cella contiene una formula
        formulaIndexes.add(resultIndex);
    }

}
