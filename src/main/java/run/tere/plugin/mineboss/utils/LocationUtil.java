package run.tere.plugin.mineboss.utils;

import org.bukkit.Location;

public class LocationUtil {
    public static Location getCenterLocation(Location location) {
        return location.clone().add(0.5, 0, 0.5);
    }
}
