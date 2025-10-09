package net.enderbyteprograms.KeepChoice.listeners;

import net.enderbyteprograms.KeepChoice.Static;
import net.enderbyteprograms.KeepChoice.Structures.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        String uuid = event.getEntity().getUniqueId().toString();
        PlayerData data = Static.Data.get(uuid);
        String currentworld =event.getEntity().getWorld().getName();

        if (!Static.Config.IsWorldAllowed(event.getEntity().getWorld().getName())) {
            return;
        }

        if (data.WorldData.containsKey(event.getEntity().getWorld().getName())) {

        } else {
            data.WorldData.put(
                    event.getEntity()
                            .getWorld()
                            .getName()
                    ,Static.Config.GetFor(
                            event.getEntity().
                                    getWorld().
                                    getName()));

        }
        //Static.Logger.info("Setting to "+data.WorldData.get(currentworld).toString());
        event.setKeepInventory(data.WorldData.get(currentworld));
        if (data.WorldData.get(currentworld)) {
            event.setDroppedExp(0);
            event.getDrops().clear();
        }
        event.setKeepLevel(data.WorldData.get(currentworld));

    }
}
