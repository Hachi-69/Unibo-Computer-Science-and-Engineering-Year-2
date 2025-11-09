package it.unibo.inner;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;
import it.unibo.inner.test.api.Product;
import it.unibo.inner.test.impl.ProductImpl;

import java.util.Arrays;
import java.util.List;

import static it.unibo.inner.test.Assertions.assertContentEqualsInOrder;

public class TestIterableWithPolicy {

    private TestIterableWithPolicy() {
    }

    private static <T> IterableWithPolicy<T> makeIterableWithPolicy(final T[] elements, final Predicate<T> filter) {
        return new ImplementationOfIterableWithPolicy<>(elements, filter); // TODO: return the implementation of IterableWithPolicy
    }

    private static <T> IterableWithPolicy<T> makeIterableWithPolicy(final T[] elements) {
        return new ImplementationOfIterableWithPolicy<>(elements);
    }

    public static void main(final String[] args) {
        final String[] test1 = {"pippo", "pluto", "foo", "bar"};
        // Create filters
        final Predicate<String> filterPippoPluto = new Predicate<>() {
            public boolean test(final String elem) {
                return elem.equals("pippo") || elem.equals("pluto");
            }
        };
        final Predicate<String> filterFooBar = new Predicate<>() {
            public boolean test(final String elem) {
                return elem.equals("foo") || elem.equals("bar");
            }
        };
        // Create iterables
        final IterableWithPolicy<String> evenIterable = makeIterableWithPolicy(test1, filterPippoPluto);
        final IterableWithPolicy<String> oddIterable = makeIterableWithPolicy(test1, filterFooBar);
        // Verify the filter application
        assertContentEqualsInOrder(List.of("pippo", "pluto"), evenIterable);
        assertContentEqualsInOrder(List.of("foo", "bar"), oddIterable);
        // Create reject/accept filters
        Predicate<String> filterOutAll = new Predicate<>() {
            public boolean test(String elem) {
                return false;
            }
        };
        Predicate<String> takeAll = new Predicate<>() {
            public boolean test(String elem) {
                return true;
            }
        };
        // Verify the filter application
        final IterableWithPolicy<String> emptyIterable = makeIterableWithPolicy(test1, filterOutAll);
        final IterableWithPolicy<String> allIterable = makeIterableWithPolicy(test1, takeAll);
        assertContentEqualsInOrder(List.of(), emptyIterable);
        assertContentEqualsInOrder(List.of("pippo", "pluto", "foo", "bar"), allIterable);
        // Test changes in policy
        final IterableWithPolicy<String> switchPolicy = makeIterableWithPolicy(test1);
        // By default, if no Predicate is given, the iterator should iterate all the elements
        assertContentEqualsInOrder(List.of("pippo", "pluto", "foo", "bar"), switchPolicy);
        switchPolicy.setIterationPolicy(filterOutAll);
        // After setting a new policy, the iterator should return no elements
        assertContentEqualsInOrder(List.of(), switchPolicy);
        // Test with products
        final Product prod1 = new ProductImpl("Prod 1", 0);
        final Product prod2 = new ProductImpl("Prod 2", 100);
        final Product prod3 = new ProductImpl("Prod 3", 20);
        final Product prod4 = new ProductImpl("Prod 4", 35);
        final Product prod5 = new ProductImpl("Prod 5", 450);
        final Product[] productsTest = {prod1, prod2, prod3, prod4, prod5};
        final Predicate<Product> filterAllAvailable = new Predicate<Product>() {
            public boolean test(final Product elem) {
                return elem.getQuantity() > 0;
            }
        };
        final Predicate<Product> filterGraterThanFifty = new Predicate<Product>() {
            public boolean test(final Product elem) {
                return elem.getQuantity() > 50;
            }
        };
        final Predicate<Product> takeOnlyProductOne = new Predicate<Product>() {
            public boolean test(final Product elem) {
                return elem.getName().equals("Prod 1");
            }
        };
        final IterableWithPolicy<Product> availableProducts = makeIterableWithPolicy(productsTest, filterAllAvailable);
        assertContentEqualsInOrder(Arrays.asList(prod2, prod3, prod4, prod5), availableProducts);
        final IterableWithPolicy<Product> expensiveProducts = makeIterableWithPolicy(productsTest, filterGraterThanFifty);
        assertContentEqualsInOrder(Arrays.asList(prod2, prod5), expensiveProducts);
        final IterableWithPolicy<Product> onlyProductOne = makeIterableWithPolicy(productsTest, takeOnlyProductOne);
        assertContentEqualsInOrder(List.of(prod1), onlyProductOne);
    }
}
