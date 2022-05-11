package lol.kondr.fix;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class KondrFixed extends JavaPlugin implements Listener {
    public KondrFixed() {
        this.log = Logger.getLogger("Minecraft");
    }
    
    Logger log;
    public void onEnable() {
    	PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        log.info("Fixed WexSide Bypass EZ");
    }
	@EventHandler
	public void onMove5(PlayerMoveEvent event) {
		try {
	    	int x = event.getPlayer().getLocation().getBlockX();
	    	int y = event.getPlayer().getLocation().getBlockY();
	    	int z = event.getPlayer().getLocation().getBlockZ();
	    	
	        Location from = event.getFrom();
	        Location to = event.getTo();
	        double distX = to.getX() - from.getX();
	        double distY = to.getY() - from.getY();
	        double distZ = to.getZ() - from.getZ();
	        double finalValue = Math.hypot(distX, distZ);
	        if (!event.getPlayer().isOp()) {
		        if (!event.getPlayer().isInsideVehicle() && !event.getPlayer().isGliding()) {
			        if (event.getPlayer().getWorld().getBlockAt(x, y - 1, z).getType() == Material.ICE || event.getPlayer().getWorld().getBlockAt(x, y - 1, z).getType() == Material.PACKED_ICE) {
			        	if (finalValue > 1.5 || finalValue < -1.5) {
			        		event.setCancelled(true);
			        	}
			        } else {
			        	if (event.getPlayer().isFlying()) {
			        		if (finalValue > 1.4 || finalValue < -1.4) {
				        		event.setCancelled(true);
			        		}
			        	} else {
			        		if (finalValue > 1.1 || finalValue < -1.1) {
				        		event.setCancelled(true);
			        		}
			        	}
			        }
			        if (distY > 1) {
		        		event.setCancelled(true);
			        }
		        }
	        }
    	} catch (Error | Exception throwable) {
    		log.warning("DONT Work BYPASS?? WHATTT!!! Fixxx Kondr!!!!");
    		}
	}
}
