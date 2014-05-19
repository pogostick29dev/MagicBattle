package me.pogostick29dev.magicbattle.listeners;

import me.pogostick29dev.magicbattle.ArenaManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerLoseHunger implements Listener {

	@EventHandler
	public void onPlayerLoseHunger(FoodLevelChangeEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (ArenaManager.getInstance().getArena(((Player) e.getEntity())) != null) e.setCancelled(true);
	}
}