package com.github.imnotglitch.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import static com.github.imnotglitch.constants.GrapplerConstant.GRAPPLER;

public class GrapplerEvent implements Listener {


    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = player.getItemInHand();

        if (item.isSimilar(GRAPPLER.build())) {
            if (event.getState() == PlayerFishEvent.State.IN_GROUND) {
                player.setVelocity(player.getLocation().getDirection().multiply(2));
            }
        }
    }

}
