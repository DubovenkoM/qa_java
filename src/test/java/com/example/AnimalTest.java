package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private Animal animal = new Animal();

    // Тест для Animal.getFood()
    @Test
    void getFoodForHerbivoreTest() throws Exception {
        List<String> food = animal.getFood("Травоядное");
        assertEquals(List.of("Трава", "Различные растения"), food);
    }

    @Test
    void getFoodForPredatorTest() throws Exception {
        List<String> food = animal.getFood("Хищник");
        assertEquals(List.of("Животные", "Птицы", "Рыба"), food);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Всеядное", "Насекомоядное", "Плотоядное"})
    void getFoodThrowsExceptionForUnknownKindTest(String unknownKind) {
        Exception exception = assertThrows(Exception.class, () -> {
            animal.getFood(unknownKind);
        });

        assertTrue(exception.getMessage().contains("Неизвестный вид животного"));
    }

    @Test
    void getFamilyReturnsGenericMessageTest() {
        String family = animal.getFamily();
        String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals(expected, family);
    }
}