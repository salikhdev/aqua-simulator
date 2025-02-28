package uz.salikhdev.model;

import lombok.extern.slf4j.Slf4j;
import uz.salikhdev.service.Aquarium;

import java.util.concurrent.TimeUnit;

import static uz.salikhdev.util.Constants.INIT_DELAY_SECONDS;
import static uz.salikhdev.util.Constants.PERIOD_SECONDS;

@Slf4j
public class MaleFish extends Fish {

    private int mateSearchCounter = 0;

    public MaleFish(int lifespan, Aquarium aquarium) {
        super(lifespan, Gender.MALE, aquarium);
    }

    @Override
    public void run() {
        var fishPool = getAquarium().getFishPool();
        setScheduledTask(
                fishPool.scheduleAtFixedRate(() -> {
                    if (getLifespan() > 0) {
                        if (!isAlive()) {
                            getScheduledTask().cancel(false);
                            return;
                        }
                        if (mateSearchCounter % 4 == 0) {
                            findMate();
                        }
                        mateSearchCounter++;
                        decreaseLifespan();
                    } else {
                        die();
                    }
                }, INIT_DELAY_SECONDS, PERIOD_SECONDS, TimeUnit.SECONDS)
        );
    }

    private void findMate() {
        getAquarium().getFishes().stream()
                .filter(other -> other != this && other.getGender() == Gender.FEMALE)
                .map(fish -> (FemaleFish) fish)
                .filter(FemaleFish::tryMate)
                .findFirst()
                .ifPresent(female -> log.info("💑 {} and {} are mating!", this.getId(), female.getId()));
    }
}