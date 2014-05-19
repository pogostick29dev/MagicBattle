package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;

import org.bukkit.entity.Player;

public class Reload extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		ArenaManager.getInstance().setupArenas();
		MessageManager.getInstance().msg(p, MessageType.GOOD, "Reloaded arenas!");
	}
	
	public Reload() {
		super("Reload the arenas.", "", "r");
	}
}