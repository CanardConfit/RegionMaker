package ch.canardconfit.test;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ch.canardconfit.region.RegionMaker;
import ch.canardconfit.region.events.RegionBlockBreakEvent;
import ch.canardconfit.region.events.RegionBlockPlaceEvent;
import ch.canardconfit.region.events.RegionCreateEvent;
import ch.canardconfit.region.events.RegionDeleteEvent;
import ch.canardconfit.region.events.RegionEntityDamageEvent;
import ch.canardconfit.region.events.RegionEntityDeathEvent;
import ch.canardconfit.region.events.RegionEntityEnterEvent;
import ch.canardconfit.region.events.RegionEntityInEvent;
import ch.canardconfit.region.events.RegionEntityLeaveEvent;
import ch.canardconfit.region.events.RegionInteractAtEntityEvent;
import ch.canardconfit.region.events.RegionInteractEvent;
import ch.canardconfit.region.events.RegionPlayerChatEvent;
import ch.canardconfit.region.events.RegionPlayerDamageEvent;
import ch.canardconfit.region.events.RegionPlayerDeathEvent;
import ch.canardconfit.region.events.RegionPlayerEnterEvent;
import ch.canardconfit.region.events.RegionPlayerInEvent;
import ch.canardconfit.region.events.RegionPlayerLeaveEvent;

/**
 * <b>Sample class and test for the events and function of the plugin</b>
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class TestRegion implements Listener {
	
	/**
	 * Constructor of TestRegion
	 */
	public TestRegion() {
		RegionMaker.getInstance().getRegionManager().registerRegion(RegionMaker.getInstance(), new Location(Bukkit.getWorld("world"), 0, 10, 0), new Location(Bukkit.getWorld("world"), 0, 0, 0), "exemple", false);
	}
	
	/**
	 * Event when a region is create by an another plugin
	 * 
	 * @see RegionCreateEvent
	 * 
	 * @param event RegionCreateEvent
	 */
	@EventHandler
	public void onRegionCreate(RegionCreateEvent event) {
		Bukkit.broadcastMessage("Region " + event.getCreatedRegion().getRegionName() + " has been created by " + event.getCreator().getName());
	}
	
	/**
	 * Event when a region is delete by an another plugin
	 * 
	 * @see RegionDeleteEvent
	 * 
	 * @param event RegionDeleteEvent
	 */
	@EventHandler
	public void onRegionDelete(RegionDeleteEvent event) {
		Bukkit.broadcastMessage("Region " + event.getDeletedRegion().getRegionName() + " has been destructed by " + event.getDestructor().getName());
	}
	
	/**
	 * Event when a player is in a region
	 * 
	 * @see RegionPlayerInEvent
	 * 
	 * @param event RegionPlayerInEvent
	 */
	@EventHandler
	public void onRegionPlayerIn(RegionPlayerInEvent event) {
		Bukkit.broadcastMessage("Player "+ event.getPlayer().getName() + " has been in the region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a Entity is in a region
	 * 
	 * @see RegionEntityInEvent
	 * 
	 * @param event RegionEntityInEvent
	 */
	@EventHandler
	public void onRegionEntityIn(RegionEntityInEvent event) {
		Bukkit.broadcastMessage("Player "+ event.getEntity().getName() + " has been in the region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a player enter in a region
	 * 
	 * @see RegionPlayerEnterEvent
	 * 
	 * @param event RegionPlayerEnterEvent
	 */
	@EventHandler
	public void onRegionPlayerEnter(RegionPlayerEnterEvent event) {
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " has entered in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a Entity enter in a region
	 * 
	 * @see RegionEntityEnterEvent
	 * 
	 * @param event RegionEntityEnterEvent
	 */
	@EventHandler
	public void onRegionEntityEnter(RegionEntityEnterEvent event) {
		Bukkit.broadcastMessage("Entity " + event.getEntity().getName() + " has entered in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a player leave a region
	 * 
	 * @see RegionPlayerLeaveEvent
	 * 
	 * @param event RegionPlayerLeaveEvent
	 */
	@EventHandler
	public void onRegionPlayerLeave(RegionPlayerLeaveEvent event) {
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " has left in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a Entity leave a region
	 * 
	 * @see RegionEntityLeaveEvent
	 * 
	 * @param event RegionEntityLeaveEvent
	 */
	@EventHandler
	public void onRegionEntityLeave(RegionEntityLeaveEvent event) {
		Bukkit.broadcastMessage("Entity " + event.getEntity().getName() + " has left in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a block is broken by a player in a region
	 * 
	 * @see RegionBlockBreakEvent
	 * 
	 * @param event RegionBlockBreakEvent
	 */
	@EventHandler
	public void onRegionBlockBreak(RegionBlockBreakEvent event) {
		Bukkit.broadcastMessage("Block " + event.getBlock().getType() + " break in region " + event.getRegion().getRegionName() + " by " + event.getPlayer().getName());
	}
	
	/**
	 * Event when a block is placed by a player in a region
	 * 
	 * @see RegionBlockPlaceEvent
	 * 
	 * @param event RegionBlockPlaceEvent
	 */
	@EventHandler
	public void onRegionBlockPlace(RegionBlockPlaceEvent event) {
		Bukkit.broadcastMessage("Block " + event.getBlock().getType() + " placed in region " + event.getRegion().getRegionName() + " by " + event.getPlayer().getName());
	}
	
	/**
	 * Event when a player interacts with something in a region
	 * 
	 * @see RegionInteractEvent
	 * 
	 * @param event RegionInteractEvent
	 */
	@EventHandler
	public void onRegionInteractEvent(RegionInteractEvent event) {
		String clickblock = "";
		if (event.getClickedBlock() != null)
			clickblock = event.getClickedBlock().getType().toString();
		else
			clickblock = "null";
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " interact with " + clickblock + " in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a player interacts with Entity in a region
	 * 
	 * @see RegionInteractAtEntityEvent
	 * 
	 * @param event RegionInteractAtEntityEvent
	 */
	@EventHandler
	public void onRegionInteractAtEntityEvent(RegionInteractAtEntityEvent event) {
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " interact on " + event.getEntity().getName() + " in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a player chat in a region
	 * 
	 * @see RegionPlayerChatEvent
	 * 
	 * @param event RegionPlayerChatEvent
	 */
	@EventHandler
	public void onRegionPlayerChatEvent(RegionPlayerChatEvent event) {
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " send message " + event.getMessage() + " in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a player take damage in a region
	 * 
	 * @see RegionPlayerDamageEvent
	 * 
	 * @param event RegionPlayerDamageEvent
	 */
	@EventHandler
	public void onRegionPlayerDamageEvent(RegionPlayerDamageEvent event) {
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " attacked by " + event.getDamager().getName() + " in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a Entity take damage in a region
	 * 
	 * @see RegionEntityDamageEvent
	 * 
	 * @param event RegionEntityDamageEvent
	 */
	@EventHandler
	public void onRegionEntityDamageEvent(RegionEntityDamageEvent event) {
		Bukkit.broadcastMessage("Entity " + event.getEntity().getName() + " attacked by " + event.getDamager().getName() + " in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a Player death in a region
	 * 
	 * @see RegionPlayerDeathEvent
	 * 
	 * @param event RegionPlayerDeathEvent
	 */
	@EventHandler
	public void onRegionPlayerDeathEvent(RegionPlayerDeathEvent event) {
		Bukkit.broadcastMessage("Player " + event.getPlayer().getName() + " death in region " + event.getRegion().getRegionName());
	}
	
	/**
	 * Event when a Entity death in a region
	 * 
	 * @see RegionEntityDeathEvent
	 * 
	 * @param event RegionEntityDeathEvent
	 */
	@EventHandler
	public void onRegionEntityDeathEvent(RegionEntityDeathEvent event) {
		Bukkit.broadcastMessage("Entity " + event.getEntity().getName() + " death in region " + event.getRegion().getRegionName());
	}
}
