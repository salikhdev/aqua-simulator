import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.salikhdev.model.FemaleFish;
import uz.salikhdev.model.Fish;
import uz.salikhdev.service.Aquarium;

import java.lang.reflect.Method;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
class FemaleFishTest {

    private Aquarium aquarium;
    private FemaleFish femaleFish;

    @BeforeEach
    void setUp() {
        aquarium = mock(Aquarium.class);
        ScheduledExecutorService mockExecutor = mock(ScheduledExecutorService.class);
        when(aquarium.getFishPool()).thenReturn(mockExecutor);

        femaleFish = new FemaleFish(10, aquarium);
    }

    @Test
    void testTryMate() {
        assertTrue(femaleFish.tryMate());
    }

    @Test
    void testRunMethod() {
        femaleFish.run();
        verify(aquarium.getFishPool(), times(1))
                .scheduleAtFixedRate(any(Runnable.class), eq(0L), eq(1L), eq(TimeUnit.SECONDS));
    }

    @Test
    void testBornFish() {
        try {
            Method bornFishMethod = FemaleFish.class.getDeclaredMethod("bornFish");
            bornFishMethod.setAccessible(true);

            bornFishMethod.invoke(femaleFish);

            verify(aquarium, times(1)).addFish(any(Fish.class));
        } catch (Exception e) {
            log.error("Error bornFish : {}", e.getMessage());
        }
    }
}
