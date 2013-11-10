package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.Arena;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.Arena.ArenaState;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;
import me.pogostick29dev.magicbattle.ArenaManager;

import org.bukkit.entity.Player;

public class Join extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		if (ArenaManager.getInstance().getArena(p) != null) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "You are already in an arena!");
			return;
		}
		
		if (args.length == 0) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "You must specify an arena number!");
			return;
		}
		
		int id = -1;
		
		try { id = Integer.parseInt(args[0]); }
		catch (Exception e) { MessageManager.getInstance().msg(p, MessageType.BAD, args[0] + " is not a number!"); }
		
		Arena a = ArenaManager.getInstance().getArena(id);
		
		if (a == null) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "That arena doesn't exist!");
			return;
		}
		
		if (a.getState() == ArenaState.DISABLED || a.getState() == ArenaState.STARTED) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "That arena is " + a.getState().toString().toLowerCase() + "!");
			return;
		}
		
		a.addPlayer(p);
	}
	
	public Join() {
		super("Join an arena.", "<id>", "j");
	}
}