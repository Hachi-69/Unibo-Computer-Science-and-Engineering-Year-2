package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {

        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        static Month fromString(final String input) {
            Objects.requireNonNull(input, "Month cannot be null");
            try {
                return Month.valueOf(input);
            } catch (IllegalArgumentException iae) {
                // input not exactly the same as Month.valueOf(), continueignore
            }
            final String raw = input.trim();
            if (raw.isEmpty()) {
                throw new IllegalArgumentException("Month cannot be empty");
            }
            try {
                final int n = Integer.parseInt(raw);
                if (n >= 1 && n <= 12) {
                    return Month.values()[n - 1];
                }
            } catch (NumberFormatException nfe) {
                // input is not a number, continue
            }
            final String s = raw.toLowerCase(Locale.ROOT);
            switch (s) {
                case "january":
                case "jan":
                case "ja":
                    return JANUARY;
                case "february":
                case "feb":
                    return FEBRUARY;
                case "march":
                case "mar":
                    return MARCH;
                case "april":
                case "apr":
                    return APRIL;
                case "may":
                    return MAY;
                case "june":
                case "jun":
                    return JUNE;
                case "july":
                case "jul":
                    return JULY;
                case "august":
                case "aug":
                    return AUGUST;
                case "september":
                case "sep":
                case "sept":
                    return SEPTEMBER;
                case "october":
                case "oct":
                    return OCTOBER;
                case "november":
                case "nov":
                    return NOVEMBER;
                case "december":
                case "dec":
                    return DECEMBER;
                default:
                    Month match = null;
                    for (final Month m : Month.values()) {
                        if (m.name().toLowerCase(Locale.ROOT).startsWith(s)) {
                            if (match != null) {
                                throw new IllegalArgumentException("Ambiguous month prefix: " + raw);
                            }
                            match = m;
                        }
                    }
                    if (match != null) {
                        return match;
                    }
            }

            throw new IllegalArgumentException("Unrecognized month: " + input);
        }

    }

    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private final static class SortByDate implements Comparator<String> {

        @Override
        public int compare(final String s1, final String s2) {
            final int i1 = Month.fromString(s1).days;
            final int i2 = Month.fromString(s2).days;
            return Integer.compare(i1, i2);
        }

    }

    private final static class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
    }
}
