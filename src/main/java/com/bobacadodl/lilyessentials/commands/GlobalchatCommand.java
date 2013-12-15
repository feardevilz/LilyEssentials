package com.bobacadodl.lilyessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.bobacadodl.lilyessentials.LilyEssentials;

public class GlobalchatCommand implements CommandExecutor 
{

	private LilyEssentials plugin;

	public GlobalchatCommand(LilyEssentials plugin) 
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{

		if(!sender.hasPermission("lilyessentials.general.chat")) 
		{
			return false;
		}

		//toggle global chat
		if(args.length <= 0) 
		{
			if(plugin.getGlobalChat().contains(sender.getName()))
			{
				plugin.getGlobalChat().remove(sender.getName());
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getCfg().format_globalchatoff));
			} else {
				plugin.getGlobalChat().add(sender.getName());
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getCfg().format_globalchaton));
			}
			return true;
		}

		String message = plugin.wordsToString(0,args);

		// we're going to use display name here -feardevilz
		if(sender instanceof Player) 
		{
			plugin.request("lilyessentials.global", ((Player)sender).getDisplayName() + "\0" + message);
		}
		else
		{
			plugin.request("lilyessentials.global", sender.getName() + "\0" + message);
		}

		return true;
	}

}
