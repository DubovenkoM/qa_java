package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatTest {

    @Mock
    private Feline mockFeline;

    private Cat cat;

    @BeforeEach
    void setUp() {
        cat = new Cat(mockFeline);
    }

    // Тест 1: Проверка голоса
    @Test
    void getSoundReturnsMeowTest() {
        String sound = cat.getSound();
        assertEquals("Мяу", sound);
    }

    // Тест 2: Получение еды. Mock
    @Test
    void getFoodCallsEatMeatOnFelineTest() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(mockFeline.eatMeat()).thenReturn(expectedFood);
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
        verify(mockFeline, times(1)).eatMeat();
    }

    // Тест 3: Исключение от Feline
    @Test
    void getFoodPropagatesExceptionTest() throws Exception {
        Exception expectedException = new Exception("Ошибка в Feline");
        when(mockFeline.eatMeat()).thenThrow(expectedException);

        Exception actualException = assertThrows(Exception.class, () -> {
            cat.getFood();
        });

        assertEquals(expectedException, actualException);
        verify(mockFeline, times(1)).eatMeat();
    }
}
