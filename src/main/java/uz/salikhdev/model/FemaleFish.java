package uz.salikhdev.model;

import lombok.extern.slf4j.Slf4j;
import uz.salikhdev.service.Aquarium;
import uz.salikhdev.util.RandomUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static uz.salikhdev.util.Constants.INIT_DELAY_SECONDS;
import static uz.salikhdev.util.Constants.PERIOD_SECONDS;


@Slf4j
public class FemaleFish extends Fish {

    private final AtomicBoolean isMating = new AtomicBoolean(false);

    public FemaleFish(int lifespan, Aquarium aquarium) {
        super(lifespan, Gender.FEMALE, aquarium);
    }

    @Override
    public void run() {
        var fishPool = getAquarium().getFishPool();

        fishPool.scheduleAtFixedRate(() -> {
            if (getLifespan() > 0) {
                decreaseLifespan();
            } else {
                die();
            }
        }, INIT_DELAY_SECONDS, PERIOD_SECONDS, TimeUnit.SECONDS);
    }

    public boolean tryMate() {
        if (isMating.compareAndSet(false, true)) {
            bornFish();
            isMating.set(false);
            return true;
        }
        return false;
    }

    public void bornFish() {
        Gender gender = RandomUtil.randomGender();
        int lifespan = RandomUtil.randomLifespan();
        Fish baby = (gender == Gender.MALE) ? new MaleFish(lifespan, getAquarium()) : new FemaleFish(lifespan, getAquarium());
        getAquarium().addFish(baby);
        log.warn("A new {} fish (ID: {}) is born in the aquarium!", gender, getId());
    }

}
