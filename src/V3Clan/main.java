package V3Clan;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
		regListener();
		
		regCommands();
		
		//loadConfig();
		
		loadMessages();
		super.onEnable();
	}
	
	private void loadMessages() {
		// TODO Auto-generated method stub
		msg.loadMSG();
	}

	@SuppressWarnings("unused")
	private void loadConfig() {
		// TODO Auto-generated method stub
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		
	}

	private void regCommands() {
		// TODO Auto-generated method stub
		getCommand("clan").setExecutor(new Commands.clanCMD(this));
		getCommand("check").setExecutor(new Commands.checkCMD(this));
	}

	private void regListener() {
		// TODO Auto-generated method stub
		new Demage.Events(this);
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
	
}
