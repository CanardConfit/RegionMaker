package ch.canardconfit.region.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.sun.xml.internal.ws.api.Cancelable;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a player take damage in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionPlayerDamageEvent extends Event implements Cancelable {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * player damage in a region
	 */
	private Player player;
	
	/**
	 * region that the player damage
	 */
	private Region region;
	
	/**
	 * Damager of the player
	 */
	private Entity damager;
	
	/**
	 * Cause of Damage
	 */
	private DamageCause cause;
	
	/**
	 * Damage final
	 */
	private double finalDamage;
	
	/**
	 * If the event is cancelled
	 */
	private boolean isCancelled;
	
	/**
	 * Constructor of event
	 * 
	 * @param reg Region of event
	 * @param player Player of event
	 * @param damager Entity damaged player
	 * @param cause Damage cause
	 * @param finalDamage Damage final
	 */
	public RegionPlayerDamageEvent(Region reg, Player player, Entity damager, DamageCause cause, double finalDamage) {
		this.isCancelled = false;
		this.region = reg;
		this.player = player;
		this.damager = damager;
		this.cause = cause;
		this.finalDamage = finalDamage;
	}

	/**
	 * Get player
	 * 
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Get region
	 * 
	 * @return Region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * Get Damager of Player
	 * 
	 * @return Entity
	 */
	public Entity getDamager() {
		return damager;
	}

	/**
	 * Get Cause of Damage
	 * 
	 * @return DamageCause
	 */
	public DamageCause getCause() {
		return cause;
	}
	
	/**
	 * Get Final Damage
	 * 
	 * @return finalDamage
	 */
	public double getFinalDamage() {
		return finalDamage;
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * if event is cancelled
	 * 
	 * @return isCancelled
	 */
	public boolean isCancelled() {
        return isCancelled;
    }

	/**
	 * Set event cancelation
	 * 
	 * @param cancelled boolean, true to cancel
	 */
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

	@Override
	public void cancel(boolean cancelled) {
        this.isCancelled = cancelled;
	}
}
