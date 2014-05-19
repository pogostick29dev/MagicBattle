package me.pogostick29dev.magicbattle.commands;

import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;
import me.pogostick29dev.magicbattle.SettingsManager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetLocation extends MagicCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "You did not specify an arena ID.");
			return;
		}
		
		int id = -1;
		
		try {
			id = Integer.parseInt(args[0]);
		} catch (Exception e) {
			MessageManager.getInstance().msg(p, MessageType.BAD, args[0] + " is not a valid number!");
			return;
		}
		
		if (!SettingsManager.getArenas().contains("arenas." + id)) {
			MessageManager.getInstance().msg(p, MessageType.BAD, "There is no arena with ID " + id + "!");
			return;
		}
		
		ConfigurationSection s = SettingsManager.getArenas().createConfigurationSection("arenas." + id + ".spawn");
		
		s.set("world", p.getWorld().getName());
		s.set("x", p.getLocation().getX());
		s.set("y", p.getLocation().getY());
		s.set("z", p.getLocation().getZ());
		
		SettingsManager.getArenas().set("arenas." + id + ".spawn", s);
		
		ArenaManager.getInstance().setupArenas();
		
		MessageManager.getInstance().msg(p, MessageType.GOOD, "Set spawn for arena " + id + "!");
	}
	
	public SetLocation() {
		super("Set the spawn location.", "<id>", "s", "sloc", "location", "loc");
	}
}