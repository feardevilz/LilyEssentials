package com.bobacadodl.lilyessentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener 
{

	private LilyEssentials plugin;

	public PlayerListener(LilyEssentials plugin) 
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) 
	{
		Player player = e.getPlayer();
		if (plugin.getAdminChat().contains(player.getName())) 
		{
			if (e.getMessage().contains("|")) 
			{
				player.sendMessage(plugin.prefix + ChatColor.DARK_RED + "Your message cannot contain: '|'");
				return;
			}
			plugin.request("lilyessentials.admin", player.getName() + "\0" + e.getMessage());
			e.setCancelled(true);
		}
		else if (plugin.getGlobalChat().contains(player.getName())) 
		{
			if (e.getMessage().contains("|")) 
			{
				player.sendMessage(plugin.prefix + ChatColor.DARK_RED + "Your message cannot contain: '|'");
				return;
			}
			plugin.request("lilyessentials.global", player.getName() + "\0" + e.getMessage());
			e.setCancelled(true);
		}
	}

	// added in matthijs110s awesome redirect code
    /* Testing this now. its not ready for production yet
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent e) 
    {
    
           Player player = e.getPlayer();

        if (e.getResult() == PlayerLoginEvent.Result.KICK_FULL) 
        {
                e.allow();
        }
        if (Bukkit.getServer().getMaxPlayers() <= Bukkit.getServer()
                        .getOnlinePlayers().length) 
        {
                if (e.getPlayer().hasPermission("lilyessentials.admin.restart")) 
                {
                        e.allow();

                } else {
                	plugin.redirectRequest(plugin.getCfg().redirectserver, player);
                }
        }
    }
    */
		
	@EventHandler
	public void onQuit(PlayerQuitEvent e) 
	{
		Player player = e.getPlayer();
		if (plugin.getLastMessaged().containsKey(player.getName())) 
		{
			plugin.getLastMessaged().remove(player.getName());
		}
	}

}
