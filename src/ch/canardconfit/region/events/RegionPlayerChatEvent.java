package ch.canardconfit.region.events;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.sun.xml.internal.ws.api.Cancelable;

import ch.canardconfit.region.tools.Region;

/**
 * Event when a player chat in a region
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class RegionPlayerChatEvent extends Event implements Cancelable {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * player chat in a region
	 */
	private Player player;
	
	/**
	 * region that the player chat
	 */
	private Region region;
	
	/**
	 * message
	 */
	private String message;
	
	/**
	 * format of message
	 */
	private String format;
	
	/**
	 * recipients of players
	 */
	private Set<Player> recipients;
	
	/**
	 * If the event is cancelled
	 */
	private boolean isCancelled;
	
	/**
	 * Constructor of event
	 * 
	 * @param reg Region of event
	 * @param player Player of event
	 * @param string message of Player
	 * @param format format of message
	 * @param recipients recipients of event
	 */
	public RegionPlayerChatEvent(Region reg, Player player, String string, String format, Set<Player> recipients) {
		this.isCancelled = false;
		this.region = reg;
		this.player = player;
		this.message = string;
		this.format = format;
		this.recipients = recipients;
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
	 * Get Message
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Get Format of message
	 * 
	 * @return format
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * Get Recipients
	 * 
	 * @return recipients
	 */
	public Set<Player> getRecipients() {
		return recipients;
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
