package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.Arena;
import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.Arena.ArenaState;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;

import org.bukkit.entity.Player;

public class ForceStart extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "You must specify an arena ID.");
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
			MessageManager.getInstance().msg(p, MessageType.BAD, "Arena " + id + " has already started!");
			return;
		}

		a.start();
		MessageManager.getInstance().msg(p, MessageType.GOOD, "Force started arena " + a.getID() + "!");
	}
	
	public ForceStart() {
		super("Force start an arena.", "<id>", "fstart", "start");
	}
}