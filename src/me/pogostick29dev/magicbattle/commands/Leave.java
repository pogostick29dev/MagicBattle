package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;

import org.bukkit.entity.Player;

public class Leave extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		if (ArenaManager.getInstance().getArena(p) == null) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "You are not already in an arena!");
			return;
		}
		
		ArenaManager.getInstance().getArena(p).removePlayer(p);
	}
	
	public Leave() {
		super("Leave an arena.", "", "l");
	}
}