package me.pogostick29dev.magicbattle.listeners;

import me.pogostick29dev.magicbattle.ArenaManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (ArenaManager.getInstance().getArena(e.getPlayer()) == null) return;
		e.setCancelled(true);
	}
}