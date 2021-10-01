package run.tere.plugin.mineboss.handlers;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class MineBossHandler {
    private List<Location> eggLocations;

    public MineBossHandler() {
        this.eggLocations = new ArrayList<>();
    }

    public List<Location> getEggLocations() {
        return eggLocations;
    }

    public void addEggLocation(Location location) {
        this.eggLocations.add(location);
    }

    public void removeEggLocation(Location location) {
        this.eggLocations.remove(location);
    }

    public boolean containsEggLocation(Location location) {
        return this.eggLocations.contains(location);
    }
}
