package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LionTest {

    @Mock
    private AnimalFamily mockAnimalFamily;

    @Mock
    private Predator mockPredator;

    private Lion lion;

    // Метод для создания Lion с моками
    private Lion createLionWithMocks() {
        try {
            return new Lion("Самец", mockAnimalFamily, mockPredator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Тест 1: ПАРАМЕТРИЗОВАННЫЙ ТЕСТ для конструктора с разными полами
    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void lionConstructorSetsManeCorrectlyTest(String sex, boolean expectedHasMane) throws Exception {
        Lion lion = new Lion(sex, mockAnimalFamily, mockPredator);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    // Тест 2: Проверка исключения при неверном поле
    @ParameterizedTest
    @ValueSource(strings = {"Мужской", "Женский", "", "Unknown"})
    void lionConstructorThrowsExceptionForInvalidSexTest(String invalidSex) {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(invalidSex, mockAnimalFamily, mockPredator);
        });

        assertTrue(exception.getMessage().contains("Используйте допустимые значения"));
    }

    // Тест 3: Проверка getKittens() через mock
    @Test
    void getKittensCallsAnimalFamilyTest() {
        // Arrange
        when(mockAnimalFamily.getKittens()).thenReturn(5);
        Lion lion = createLionWithMocks();
        int kittens = lion.getKittens();
        assertEquals(5, kittens);
        verify(mockAnimalFamily, times(1)).getKittens();
    }

    // Тест 4: Проверка getFamily() через mock
    @Test
    void getFamilyCallsAnimalFamilyTest() {
        when(mockAnimalFamily.getFamily()).thenReturn("Львиные");
        Lion lion = createLionWithMocks();
        String family = lion.getFamily();
        assertEquals("Львиные", family);
        verify(mockAnimalFamily, times(1)).getFamily();
    }

    // Тест 5: Проверка getFood() через mock
    @Test
    void getFoodCallsPredatorTest() throws Exception {
        List<String> expectedFood = List.of("Антилопа", "Зебра");
        when(mockPredator.eatMeat()).thenReturn(expectedFood);
        Lion lion = createLionWithMocks();
        List<String> food = lion.getFood();
        assertEquals(expectedFood, food);
        verify(mockPredator, times(1)).eatMeat();
    }

    // Тест 6: Проверка исключения от Predator
    @Test
    void getFoodPropagatesExceptionTest() throws Exception {
        Exception expectedException = new Exception("Нет еды");
        when(mockPredator.eatMeat()).thenThrow(expectedException);
        Lion lion = createLionWithMocks();
        Exception actualException = assertThrows(Exception.class, () -> {
            lion.getFood();
        });
        assertEquals(expectedException, actualException);
    }
}
