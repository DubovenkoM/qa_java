package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FelineTest {

    private Feline feline;

    @BeforeEach
    void setUp() {
        feline = new Feline();
    }

    // Тест 1: Проверка метода eatMeat()
    @Test
    void eatMeatReturnsCorrectFoodForPredatorTest() throws Exception {
        List<String> food = feline.eatMeat();
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }
    // Тест 2: Семейства
    @Test
    void getFamilyReturnsCorrectFamilyTest() {
        String family = feline.getFamily();
        assertEquals("Кошачьи", family);
    }

    // Тест 3: getKittens() без параметров
    @Test
    void getKittensWithoutParameterReturnsOneTest() {
        int kittens = feline.getKittens();
        assertEquals(1, kittens);
    }
    // Тест 4: ПАРАМЕТРИЗОВАННЫЙ ТЕСТ для getKittens(int)
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 5, 10})
    void getKittensWithParameterReturnsCorrectValueTest(int expectedCount) {
        int actualCount = feline.getKittens(expectedCount);
        assertEquals(expectedCount, actualCount);
    }

    // Тест 5: Проверка реализации интерфесов у Feline
    @Test
    void felineImplementsInterfacesTest() {
        assertTrue(feline instanceof Predator);
        assertTrue(feline instanceof AnimalFamily);
    }
}
