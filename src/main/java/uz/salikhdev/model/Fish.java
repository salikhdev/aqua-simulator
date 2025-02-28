package uz.salikhdev.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import uz.salikhdev.service.Aquarium;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static uz.salikhdev.util.Constants.LIFESPAN_DECREASE_RATE;

@Slf4j
@Getter
@Setter
public abstract class Fish implements Runnable {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    private final int id;
    private int lifespan;
    private boolean isAlive;
    private Aquarium aquarium;
    private Gender gender;
    private ScheduledFuture<?> scheduledTask;

    public Fish(int lifespan, Gender gender, Aquarium aquarium) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.gender = gender;
        this.isAlive = true;
        this.lifespan = lifespan;
        this.aquarium = aquarium;
    }

    protected void decreaseLifespan() {
        setLifespan(getLifespan() - LIFESPAN_DECREASE_RATE);
    }

    protected void die() {
        if (!isAlive) {
            return;
        }
        isAlive = false;
        log.error("{} {} has died.", gender.name(), id);
        aquarium.removeFish(this);
    }
}
