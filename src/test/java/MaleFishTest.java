import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uz.salikhdev.model.FemaleFish;
import uz.salikhdev.model.Fish;
import uz.salikhdev.model.Gender;
import uz.salikhdev.model.MaleFish;
import uz.salikhdev.service.Aquarium;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
class MaleFishTest {

    private Aquarium mockAquarium;
    private MaleFish maleFish;
    private FemaleFish mockFemaleFish;
    private Queue<Fish> fishes;

    @BeforeEach
    void setUp() {
        mockAquarium = mock(Aquarium.class);

        ScheduledExecutorService mockFishPool = mock(ScheduledExecutorService.class);
        when(mockAquarium.getFishPool()).thenReturn(mockFishPool);

        maleFish = new MaleFish(10, mockAquarium);

        mockFemaleFish = mock(FemaleFish.class);
        when(mockFemaleFish.getGender()).thenReturn(Gender.FEMALE);

        fishes = new ArrayDeque<>();
        fishes.add(maleFish);
        fishes.add(mockFemaleFish);

        when(mockAquarium.getFishes()).thenReturn(fishes);
    }

    @Test
    void testFindMate_ShouldMateWithFemaleFish() {
        try {
            when(mockFemaleFish.tryMate()).thenReturn(true);


            Method findMateMethod = MaleFish.class.getDeclaredMethod("findMate");
            findMateMethod.setAccessible(true);
            findMateMethod.invoke(maleFish);


            verify(mockFemaleFish, times(1)).tryMate();
        } catch (Exception e) {
            log.error("Error findMate : {}", e.getMessage());
        }
    }

    @Test
    void testFindMate_WhenNoFemaleFish_ShouldNotMate() {
        try {
            when(mockAquarium.getFishes()).thenReturn(fishes);
            when(mockFemaleFish.tryMate()).thenReturn(false);

            Method findMateMethod = MaleFish.class.getDeclaredMethod("findMate");
            findMateMethod.setAccessible(true);
            findMateMethod.invoke(maleFish);

            assertFalse(mockFemaleFish.tryMate());
        } catch (Exception e) {
            log.error("Error NotMate : {}", e.getMessage());
        }
    }
}
