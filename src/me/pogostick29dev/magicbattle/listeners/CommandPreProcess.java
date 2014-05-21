package me.pogostick29dev.magicbattle.listeners;

import me.pogostick29dev.magicbattle.Arena;
import me.pogostick29dev.magicbattle.ArenaManager;
import me.pogostick29dev.magicbattle.MessageManager;
import me.pogostick29dev.magicbattle.MessageManager.MessageType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreProcess implements Listener {

  @EventHandler
  public void onCommand(PlayerCommandPreprocessEvent  e) {
    Arena a = ArenaManager.getInstance().getArena(e.getPlayer());
    Player p = null;
    if(a != null) {
    	if(!e.getMessage().equalsIgnoreCase("/mg leave") 
    	        || !e.getMessage().equalsIgnoreCase("/magicbattle leave")) {
    	          
    	MessageManager.getInstance().msg(p, MessageType.BAD, "You cannot do that command! Please do /mg leave before doing any command!");
    	}
    }
    
  }

}
