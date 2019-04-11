package ch.canardconfit.region.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import ch.canardconfit.region.RegionMaker;
import ch.canardconfit.region.events.RegionBlockBreakEvent;
import ch.canardconfit.region.events.RegionBlockPlaceEvent;
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
import ch.canardconfit.region.manager.RegionManager;
import ch.canardconfit.region.tools.Region;

/**
 * <b>Class to check the plugin's vents and run them at the right time</b>
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionListener implements Listener {
	
	/**
	 * RegionManager current instance
	 * 
	 * @see RegionManager
	 */
	private RegionManager rm;
	
	/**
	 * old location of entitys
	 */
	private Map<Entity, Location> oldLocationEntity = new HashMap<>();

	/**
	 * old location of players
	 */
	private Map<Player, Location> oldLocationPlayer = new HashMap<>();
	
	/**
	 * Constructor of RegionListener
	 * 
	 * @param rm current RegionManager instance
	 */
	@SuppressWarnings("deprecation")
	public RegionListener(RegionManager rm) {
		this.rm = rm;
		
		//Scheduler for entitys move detection
		Bukkit.getScheduler().scheduleSyncRepeatingTask(RegionMaker.getInstance(), new BukkitRunnable() {

			@Override
			public void run() {
				
				//Loop all worlds
				for (World w : Bukkit.getWorlds()) {
					
					//Loop all entitys in this world
					for (Entity e : w.getEntities()) {
						
						//If the entity isn't a player
						if (!(e instanceof Player)) {
							
							//get region of location of entity
							Region reg = rm.getRegion(e.getLocation());
							
							if (reg != null) {
								
								//If entity is enter into a region, create RegionEntityEnterEvent
								if (oldLocationEntity.containsKey(e) &&
									!rm.isInRegion(reg, oldLocationEntity.get(e))) {
										
									RegionEntityEnterEvent ree = new RegionEntityEnterEvent(reg, e);
									Bukkit.getPluginManager().callEvent(ree);
									
									if (ree.isCancelled()) {
										e.teleport(oldLocationEntity.get(e));
									}
								}
								
								/*Create RegionEntityInEvent*/
								
								RegionEntityInEvent eventC = new RegionEntityInEvent(reg, e);
								Bukkit.getPluginManager().callEvent(eventC);
								
								if (eventC.isCancelled()) {
									e.teleport(oldLocationEntity.get(e));
								}
								
							//If entity isn't in a region
							} else {
								
								 //If entity has just left a region, create RegionEntityLeaveEvent 
								 if (oldLocationEntity.containsKey(e)) {
									 Region region = rm.getRegion(oldLocationEntity.get(e));
									 if (region != null) {
										 RegionEntityLeaveEvent eventC = new RegionEntityLeaveEvent(region, e);
										 Bukkit.getPluginManager().callEvent(eventC);
											
										if (eventC.isCancelled()) {
											e.teleport(oldLocationEntity.get(e));
										}
									 }
								 }
							}
							
							//Save the last location of entity
							if (oldLocationEntity.containsKey(e))
								oldLocationEntity.remove(e);
							oldLocationEntity.put(e, e.getLocation());
						}
					}
				}
			}
		}, 0, 1);
	}

	/**
	 * Event to remove old location of Player
	 * 
	 * @param event PlayerQuitEvent
	 */
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if (oldLocationPlayer.containsKey(event.getPlayer()))
			oldLocationPlayer.remove(event.getPlayer());
	}
	
	/**
	 * Event to create RegionEntityDeathEvent if a entity died in region
	 * 
	 * @see RegionEntityDeathEvent
	 * 
	 * @param event EntityDeathEvent
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (oldLocationEntity.containsKey(event.getEntity()))
			oldLocationEntity.remove(event.getEntity());
		Region reg = rm.getRegion(event.getEntity().getLocation());
		if (reg != null) {
			RegionEntityDeathEvent rpde = new RegionEntityDeathEvent(reg, event.getEntity(), event.getDroppedExp(), event.getDrops());
			Bukkit.getPluginManager().callEvent(rpde);
		}
	}
	
	/**
	 * Event to create RegionPlayerDeathEvent if a player died in region
	 * 
	 * @see RegionPlayerDeathEvent
	 * 
	 * @param event PlayerDeathEvent
	 */
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Region reg = rm.getRegion(event.getEntity().getLocation());
		if (reg != null) {
			RegionPlayerDeathEvent rpde = new RegionPlayerDeathEvent(reg, event.getEntity(), event.getDroppedExp(), event.getDrops());
			Bukkit.getPluginManager().callEvent(rpde);
		}
	}

	/**
	 * Event to create RegionPlayerDamageEvent or RegionEntityDamageEvent if a entity or player take damage in region
	 * 
	 * @see RegionPlayerDamageEvent
	 * @see RegionEntityDamageEvent
	 * 
	 * @param event EntityDamageByEntityEvent
	 */
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		Region reg = rm.getRegion(event.getEntity().getLocation());
		if (reg != null) {
			if (event.getEntity() instanceof Player) {
				RegionPlayerDamageEvent rpde = new RegionPlayerDamageEvent(reg, (Player) event.getEntity(), event.getDamager(), event.getCause(), event.getFinalDamage());
				Bukkit.getPluginManager().callEvent(rpde);
				
				if (rpde.isCancelled()) event.setCancelled(true);
			} else {
				RegionEntityDamageEvent rpde = new RegionEntityDamageEvent(reg, event.getEntity(), event.getDamager(), event.getCause(), event.getFinalDamage());
				Bukkit.getPluginManager().callEvent(rpde);
				
				if (rpde.isCancelled()) event.setCancelled(true);
			}
		}
	}

	/**
	 * Event to create RegionPlayerEnterEvent, RegionPlayerInEvent or RegionPlayerLeaveEvent if player enter, is in or leave a region
	 * 
	 * @see RegionPlayerEnterEvent
	 * @see RegionPlayerInEvent
	 * @see RegionPlayerLeaveEvent
	 * 
	 * @param event PlayerMoveEvent
	 */
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Location locp = event.getPlayer().getLocation();
		
		Region reg = rm.getRegion(locp);
		
		if (reg != null) {
			
			if (oldLocationPlayer.containsKey(event.getPlayer()) &&
			    !rm.isInRegion(reg, oldLocationPlayer.get(event.getPlayer()))) {
				
				RegionPlayerEnterEvent ree = new RegionPlayerEnterEvent(reg, event.getPlayer());
				Bukkit.getPluginManager().callEvent(ree);
				
				if (ree.isCancelled()) event.setCancelled(true);
			}
			
			RegionPlayerInEvent eventC = new RegionPlayerInEvent(reg, event.getPlayer());
			Bukkit.getPluginManager().callEvent(eventC);
			
			if (eventC.isCancelled()) event.setCancelled(true);
		} else {
			 if (oldLocationPlayer.containsKey(event.getPlayer())) {
				 Region region = rm.getRegion(oldLocationPlayer.get(event.getPlayer()));
				 if (region != null) {
					 RegionPlayerLeaveEvent eventC = new RegionPlayerLeaveEvent(region, event.getPlayer());
					 Bukkit.getPluginManager().callEvent(eventC);
						
					if (eventC.isCancelled()) event.setCancelled(true);
				 }
			 }
		}
		if (oldLocationPlayer.containsKey(event.getPlayer()))
			oldLocationPlayer.remove(event.getPlayer());
		oldLocationPlayer.put(event.getPlayer(), event.getPlayer().getLocation());
	}

	/**
	 * Event to create RegionBlockBreakEvent if a block has broken in a region
	 * 
	 * @see RegionBlockBreakEvent
	 * 
	 * @param event BlockBreakEvent
	 */
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Region reg = rm.getRegionBlock(event.getBlock().getLocation());
		if (reg != null) {
			RegionBlockBreakEvent rbbe = new RegionBlockBreakEvent(reg, event.getBlock(), event.getPlayer());
			Bukkit.getPluginManager().callEvent(rbbe);

			if (rbbe.isCancelled()) event.setCancelled(true);
		}
	}

	/**
	 * Event to create RegionBlockPlaceEvent if a block has placed in a region
	 * 
	 * @see RegionBlockPlaceEvent
	 * 
	 * @param event BlockPlaceEvent
	 */
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Region reg = rm.getRegionBlock(event.getBlock().getLocation());
		if (reg != null) {
			RegionBlockPlaceEvent rbpe = new RegionBlockPlaceEvent(reg, event.getBlock(), event.getPlayer());
			Bukkit.getPluginManager().callEvent(rbpe);
			
			if (rbpe.isCancelled()) event.setCancelled(true);
		}
	}

	/**
	 * Event to create RegionInteractEvent if a player interact with something in a region
	 * 
	 * @see RegionInteractEvent
	 * 
	 * @param event PlayerInteractEvent
	 */
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Region reg = rm.getRegion(event.getPlayer().getLocation());
		if (reg != null) {
			RegionInteractEvent rie = new RegionInteractEvent(reg, event.getAction(), event.getClickedBlock(), event.getPlayer(), event.getItem(), event.getBlockFace(), event.getMaterial());
			Bukkit.getPluginManager().callEvent(rie);

			if (rie.isCancelled()) event.setCancelled(true);
		}
	}

	/**
	 * Event to create RegionInteractAtEntityEvent if a player interact with entity in a region
	 * 
	 * @see RegionInteractAtEntityEvent
	 * 
	 * @param event PlayerInteractAtEntityEvent
	 */
	@EventHandler
	public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
		Region reg = rm.getRegion(event.getPlayer().getLocation());
		if (reg != null) {
			RegionInteractAtEntityEvent rie = new RegionInteractAtEntityEvent(reg, event.getPlayer(), event.getClickedPosition(), event.getRightClicked());
			Bukkit.getPluginManager().callEvent(rie);
			
			if (rie.isCancelled()) event.setCancelled(true);
		}
	}

	/**
	 * Event to create RegionPlayerChatEvent if a player chat in a region
	 * 
	 * @see RegionPlayerChatEvent
	 * 
	 * @param event AsyncPlayerChatEvent
	 */
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Region reg = rm.getRegion(event.getPlayer().getLocation());
		if (reg != null) {
			RegionPlayerChatEvent rpce = new RegionPlayerChatEvent(reg, event.getPlayer(), event.getMessage(), event.getFormat(), event.getRecipients());
			Bukkit.getPluginManager().callEvent(rpce);
			
			if (rpce.isCancelled()) event.setCancelled(true);
		}
	}
}
