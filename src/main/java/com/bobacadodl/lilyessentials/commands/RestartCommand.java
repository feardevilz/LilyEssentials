package com.bobacadodl.lilyessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.bobacadodl.lilyessentials.LilyEssentials;

public class RestartCommand implements CommandExecutor 
{

	private LilyEssentials plugin;

	public RestartCommand(LilyEssentials plugin) 
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{

		if (!sender.hasPermission("lilyessentials.admin.restart")) 
		{
			return false;
		}

		// Invalid
		if (args.length <= 0) 
		{
			sender.sendMessage(plugin.prefix + ChatColor.RED + "Proper Usage: " + ChatColor.YELLOW + "/restartserver");
			return true;
		}

		String server = plugin.getCfg().redirectserver;
		// enable bukkit whitelist to prevent people from reconnecting to us..
		// TODO: broadcast, whitelist
		sender.sendMessage(plugin.prefix + ChatColor.RED + "Teleporting all players to server: " + ChatColor.YELLOW + server);
		plugin.request("lilyessentials.restart", server);

		return true;
	}
	
}
