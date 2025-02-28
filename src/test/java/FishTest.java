import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.salikhdev.model.Fish;
import uz.salikhdev.model.Gender;
import uz.salikhdev.service.Aquarium;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
class FishTest {
    private Aquarium mockAquarium;
    private Fish fish;

    @BeforeEach
    void setUp() {
        mockAquarium = mock(Aquarium.class);
        fish = new TestFish(10, Gender.MALE, mockAquarium);
    }

    @Test
    void testInitialState() {
        assertNotNull(fish);
        assertTrue(fish.isAlive());
        assertEquals(10, fish.getLifespan());
        assertEquals(Gender.MALE, fish.getGender());
    }

    @Test
    void testDecreaseLifespan() {
        try {
            Method method = Fish.class.getDeclaredMethod("decreaseLifespan");
            method.setAccessible(true);
            method.invoke(fish);

            assertEquals(9, fish.getLifespan());
        } catch (Exception e) {
            log.error("Error DecreaseLifespan : {}", e.getMessage());
        }
    }

    @Test
    void testDie() {
        try {
            Method method = Fish.class.getDeclaredMethod("die");
            method.setAccessible(true);
            method.invoke(fish);
            assertFalse(fish.isAlive());
            verify(mockAquarium, times(1)).removeFish(fish);
        } catch (Exception e) {
            log.error("Error Die : {}", e.getMessage());
        }
    }

    @Test
    void testDieCalledOnce() {
        try {
            Method method = Fish.class.getDeclaredMethod("die");
            method.setAccessible(true);
            method.invoke(fish);
            method.invoke(fish);

            assertFalse(fish.isAlive());
            verify(mockAquarium, times(1)).removeFish(fish);

        } catch (Exception e) {
            log.error("Error Die ignore : {}", e.getMessage());
        }
    }


    static class TestFish extends Fish {
        public TestFish(int lifespan, Gender gender, Aquarium aquarium) {
            super(lifespan, gender, aquarium);
        }

        @Override
        public void run() {
        }
    }
}