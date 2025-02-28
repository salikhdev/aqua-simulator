package uz.salikhdev.util;

import uz.salikhdev.model.Gender;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();
    private static final int MIN_LIFESPAN = 5;
    private static final int MAX_LIFESPAN = 10;

    public static int randomLifespan() {
        return random.nextInt((MAX_LIFESPAN - MIN_LIFESPAN) + 1) + MIN_LIFESPAN;
    }

    public static Gender randomGender() {
        return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }
}
