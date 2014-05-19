package me.pogostick29dev.magicbattle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import me.pogostick29dev.magicbattle.MessageManager.MessageType;
import me.pogostick29dev.magicbattle.commands.Create;
import me.pogostick29dev.magicbattle.commands.Delete;
import me.pogostick29dev.magicbattle.commands.ForceStart;
import me.pogostick29dev.magicbattle.commands.ForceStop;
import me.pogostick29dev.magicbattle.commands.Join;
import me.pogostick29dev.magicbattle.commands.Leave;
import me.pogostick29dev.magicbattle.commands.MagicCommand;
import me.pogostick29dev.magicbattle.commands.Reload;
import me.pogostick29dev.magicbattle.commands.SetLocation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

	private ArrayList<MagicCommand> cmds = new ArrayList<MagicCommand>();
	
	public CommandManager() {
		cmds.add(new Create());
		cmds.add(new Delete());
		cmds.add(new ForceStart());
		cmds.add(new ForceStop());
		cmds.add(new Join());
		cmds.add(new Leave());
		cmds.add(new Reload());
		cmds.add(new SetLocation());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			MessageManager.getInstance().msg(sender, MessageType.BAD, "Only players can use MagicBattle!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("magicbattle")) {
			if (args.length == 0) {
				for (MagicCommand mc : cmds) MessageManager.getInstance().msg(p, MessageType.INFO, "/mb " + aliases(mc) + " " + mc.getUsage() + " - " + mc.getMessage());
				return true;
			}
			
			MagicCommand c = getCommand(args[0]);
			
			if (c == null) {
				MessageManager.getInstance().msg(sender, MessageType.BAD, "That command doesn't exist!");
				return true;
			}
			
			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			c.onCommand(p, args);
			
			return true;
		}
		return true;
	}
	
	private String aliases(MagicCommand cmd) {
		String fin = "";
		
		for (String a : cmd.getAliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	private MagicCommand getCommand(String name) {
		for (MagicCommand cmd : cmds) {
			if (cmd.getClass().getSimpleName().equalsIgnoreCase(name)) return cmd;
			for (String alias : cmd.getAliases()) if (name.equalsIgnoreCase(alias)) return cmd;
		}
		return null;
	}
}