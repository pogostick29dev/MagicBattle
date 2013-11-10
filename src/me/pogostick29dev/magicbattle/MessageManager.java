package me.pogostick29dev.magicbattle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageManager {

	public enum MessageType {
		
		INFO(ChatColor.GRAY),
		GOOD(ChatColor.GOLD),
		BAD(ChatColor.RED);
		
		private ChatColor color;
		
		MessageType(ChatColor color) {
			this.color = color;
		}
		
		public ChatColor getColor() {
			return color;
		}
	}
	
	private MessageManager() { }
	
	private static MessageManager instance = new MessageManager();
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	private String prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "MagicBattle" + ChatColor.GRAY + "] " + ChatColor.RESET;
	
	public void msg(CommandSender sender, MessageType type, String... messages) {
		for (String msg : messages) {
			sender.sendMessage(prefix + type.getColor() + msg);
		}
	}
	
	public void broadcast(MessageType type, String... messages) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			for (String msg : messages) {
				p.sendMessage(prefix + type.getColor() + msg);
			}
		}
	}
}