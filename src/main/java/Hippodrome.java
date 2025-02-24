import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {

    private final List<Horse> horses;
    private static final Logger log = LogManager.getLogger(Hippodrome.class);

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            log.error("HippodroHippodrome: Horses list is null");

            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            log.error("Hippodrome: Horses list is empty");

            throw new IllegalArgumentException("Horses cannot be empty.");
        }
        log.error(" Hippodrome: Создание Hippodrome, лошадей [{}]",horses.size());
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
