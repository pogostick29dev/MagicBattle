package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.Arena;
import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.Arena.ArenaState;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;
import me.pogostick29dev.magicbattle.SettingsManager;

import org.bukkit.entity.Player;

public class Delete extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "You must specify an arena number!");
			return;
		}
		
		int id = -1;
		
		try { id = Integer.parseInt(args[0]); }
		catch (Exception e) {
			MessageManager.getInstance().msg(p, MessageType.BAD, args[0] + " is not a valid number!");
			return;
		}
		
		Arena a = ArenaManager.getInstance().getArena(id);
		
		if (a == null) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "There is no arena with ID " + id + "!");
			return;
		}
		
		if (a.getState() == ArenaState.STARTED) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "Arena " + id + " is ingame!");
			return;
		}
		
		SettingsManager.getArenas().set(id + "", null);
		
		ArenaManager.getInstance().setupArenas();
	}
	
	public Delete() {
		super("Delete an arena.", "<id>", "d");
	}
}