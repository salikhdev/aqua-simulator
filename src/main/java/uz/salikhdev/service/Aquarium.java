package uz.salikhdev.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import uz.salikhdev.model.Fish;
import uz.salikhdev.util.Constants;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
public class Aquarium {

    private final Queue<Fish> fishes = new ConcurrentLinkedQueue<>();
    private final ScheduledExecutorService fishPool = Executors.newScheduledThreadPool(Constants.AQUARIUM_LIMIT);

    public void addFish(Fish fish) {
        log.warn("{} fish {} entered the aquarium.", fish.getGender(), fish.getId());
        fishes.add(fish);
        fishPool.schedule(fish, 0, TimeUnit.SECONDS);
    }

    public void removeFish(Fish fish) {
        fishes.remove(fish);
        if (fishes.isEmpty() && !fishPool.isShutdown()) {
            log.error("All fish have died. Aquarium is empty.");
            stopSimulation();
        }
    }

    public void stopSimulation() {
        fishPool.shutdown();
        for (Fish fish : fishes) {
            if (fish.getScheduledTask() != null) {
                fish.getScheduledTask().cancel(true);
            }
        }
        fishes.clear();
        log.error("❌ Aquarium simulation stopped gracefully!");
    }
}
