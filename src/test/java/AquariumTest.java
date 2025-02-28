import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.salikhdev.model.Fish;
import uz.salikhdev.model.Gender;
import uz.salikhdev.service.Aquarium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AquariumTest {

    private Aquarium aquarium;
    private Fish mockFish1;
    private Fish mockFish2;

    @BeforeEach
    void setUp() {
        aquarium = new Aquarium();
        mockFish1 = mock(Fish.class);
        mockFish2 = mock(Fish.class);
    }

    @Test
    public void testAddFish() {
        when(mockFish1.getId()).thenReturn(1);
        when(mockFish1.getGender()).thenReturn(Gender.MALE);

        aquarium.addFish(mockFish1);

        assertTrue(aquarium.getFishes().contains(mockFish1));
    }

    @Test
    public void testRemoveFish() {
        aquarium.addFish(mockFish1);
        aquarium.removeFish(mockFish1);

        assertFalse(aquarium.getFishes().contains(mockFish1));
    }

    @Test
    public void testRemoveFish_StopsSimulationIfEmpty() {
        aquarium.addFish(mockFish1);
        aquarium.removeFish(mockFish1);

        assertTrue(aquarium.getFishes().isEmpty());
        assertTrue(aquarium.getFishPool().isShutdown());
    }

    @Test
    public void testStopSimulation() {
        aquarium.addFish(mockFish1);
        aquarium.addFish(mockFish2);

        aquarium.stopSimulation();

        assertTrue(aquarium.getFishes().isEmpty());
        assertTrue(aquarium.getFishPool().isShutdown());
    }
}

