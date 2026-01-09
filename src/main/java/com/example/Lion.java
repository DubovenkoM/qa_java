package com.example;

import java.util.List;

public class Lion {

    boolean hasMane;
    private AnimalFamily animalFamily;
    private Predator predator;

    public Lion(String sex, AnimalFamily animalFamily, Predator predator) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного - самец или самка");
        }

        this.animalFamily = animalFamily;
        this.predator = predator;
    }



    public int getKittens() {
        return animalFamily.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }
    public String getFamily() {
        return animalFamily.getFamily();
    }
}
