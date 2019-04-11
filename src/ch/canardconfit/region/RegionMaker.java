package ch.canardconfit.region;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.canardconfit.region.listener.RegionListener;
import ch.canardconfit.region.manager.RegionManager;

/**
 * <b>RegionMaker is the main class of the library</b>
 * <p>In this class, you can get the instance of RegionMaker and the instance of RegionManager</p>
 * 
 * @see RegionManager
 * 
 * @author CanardConfit
 * @version 1.0.0
 *
 */
public class RegionMaker extends JavaPlugin {

	/**
	 * Prefix of the plugin messages
	 */
	private String PREFIX = "§4Region§bMaker §c>>";
	
	/**
	 * RegionMaker instance object
	 * 
	 * @see RegionMaker
	 */
	private static RegionMaker instance;
	
	/**
	 * RegionManager instance
	 * 
	 * @see RegionManager
	 */
	private RegionManager rm;
	
	/**
	 * Main function on plugin enable
	 */
	@Override
	public void onEnable() {
		instance = this;
		
		getServer().getConsoleSender().sendMessage(PREFIX + " §eVersion §a" + getServer().getBukkitVersion() + " §eof the Server.");

		//Creation of new RegionManager
		rm = new RegionManager();

		//Register listener class
		Bukkit.getPluginManager().registerEvents(new RegionListener(rm), this);
		
		Bukkit.getConsoleSender().sendMessage(PREFIX + " §ePlugin library by CanardConfit is enabled !");
		
		//Class for testing events and function of the plugin
		//Bukkit.getPluginManager().registerEvents(new TestRegion(), this);
	}
	
	/**
	 * Main function on plugin disable
	 */
	@Override
	public void onDisable() {
		instance = null;
		getServer().getConsoleSender().sendMessage(PREFIX + " §ePlugin library by CanardConfit is disabled !");
	}
	
	
	/**
	 * Get the RegionManager instance for create, delete or get an region
	 * 
	 * @return instance of RegionManager
	 */
	public RegionManager getRegionManager() {
		return rm;
	}
	
	/**
	 * Get the RegionMaker instance for get the RegionManager
	 * 
	 * @return instance of RegionMaker
	 */
	public static RegionMaker getInstance() {
		return instance;
	}
}
