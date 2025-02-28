package uz.salikhdev;

import lombok.extern.slf4j.Slf4j;
import uz.salikhdev.model.FemaleFish;
import uz.salikhdev.model.Fish;
import uz.salikhdev.model.MaleFish;
import uz.salikhdev.service.Aquarium;
import uz.salikhdev.util.RandomUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Main {
    public static void main(String[] args) {

        log.info("🐠 Aquarium simulation started!");

        Aquarium aquarium = new Aquarium();

        int maleCount = RandomUtil.randomLifespan();
        int femaleCount = RandomUtil.randomLifespan();

        List<Fish> fishes = IntStream.range(0, maleCount)
                .mapToObj(i -> new MaleFish(RandomUtil.randomLifespan(), aquarium))
                .collect(Collectors.toList());

        fishes.addAll(IntStream.range(0, femaleCount)
                .mapToObj(i -> new FemaleFish(RandomUtil.randomLifespan(), aquarium))
                .toList());

        fishes.forEach(aquarium::addFish);
    }
}
