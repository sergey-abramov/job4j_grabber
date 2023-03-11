package ru.job4j.srp;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
