package ch.canardconfit.region.tools;

import org.bukkit.Location;

/**
 * <b>This class is used to create a region with 4 coordinates, a region name and a boolean to know if it is a region only on x and z.</b>
 * 
 * 
 * @author CanardConfit
 * @version 1.0.0
 */
public class Region {
	
	/**
	 * Location 1 of region
	 */
	private Location loc1;
	/**
	 * Location 2 of region
	 */
	private Location loc2;
	/**
	 * Location 3 of region
	 */
	private Location loc3;
	/**
	 * Location 4 of region
	 */
	private Location loc4;

	/**
	 * Name of region
	 */
	private String name;
	
	/**
	 * Boolean to know if it is a region only on x and z
	 */
	private boolean inVertical;

	/**
	 * Constructor of Region
	 * 
	 * @param loc1 an x, y, z coordinate of one of the edges of the region
	 * @param loc2 an x, y, z coordinate of the opposite of loc1
	 * @param name the name of the region
	 * @param inVertical boolean to know if "y" is taken into account in the region
	 */
	public Region(Location loc1, Location loc2, String name, boolean inVertical) {
		
		this.name = name;
		this.inVertical = inVertical;
		
		this.loc1 = loc1;
		this.loc2 = loc2;
		
		//Calcul the another edges coordinate
		this.loc3 = new Location(loc1.getWorld(), loc2.getX(), loc1.getY(), loc1.getZ());
		this.loc4 = new Location(loc1.getWorld(), loc1.getX(), loc2.getY(), loc2.getZ());
		
		//Verification so that the coordinates are always in the same direction for all regions
		if (loc1.getX() > loc3.getX() && loc2.getZ() < loc3.getZ()) {
			
			loc3 = loc4;
			loc4 = new Location(loc1.getWorld(), loc2.getX(), loc1.getY(), loc1.getZ());
			
		} else if (loc1.getX() < loc3.getX() && loc2.getZ() > loc3.getZ()) {

			loc3 = loc4;
			loc4 = new Location(loc1.getWorld(), loc2.getX(), loc1.getY(), loc1.getZ());
			
		}
	}
	
	/**
	 * Get the boolean if "y" is taken into account in the region.
	 * 
	 * @see inVertical
	 * 
	 * @return inVertical
	 */
	public boolean inVertical() {
		return inVertical;
	}
	
	/**
	 * Get the name of region
	 * 
	 * @return the name of region
	 */
	public String getRegionName() {
		return name;
	}
	
	/**
	 * Get the coordinates of edges of the region
	 * 
	 * @return a list with the 4 coordinates (loc1, loc2, loc3, loc4)
	 */
	public Location[] getLocs() {
		return new Location[] {loc1, loc2, loc3, loc4};
	}
}
