package net.dejinn.septemberRainCore.JavaShit;

import org.bukkit.Location;

public class YawCardinal {
    public static Location roundLocation(Location loc){
        Location roundedLoc = loc;
        float yaw = loc.getYaw();
        yaw = (yaw % 360 + 360) % 360;
        if (yaw > 135 || yaw < -135) {
            roundedLoc.setYaw(180);
        } else if (yaw < -45) {
            roundedLoc.setYaw(-90);
        } else if (yaw > 45) {
            roundedLoc.setYaw(90);
        } else {
            roundedLoc.setYaw(0);
        }
        return roundedLoc;
    }
}
