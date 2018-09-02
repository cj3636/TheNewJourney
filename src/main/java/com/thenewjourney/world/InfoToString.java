package com.thenewjourney.world;

public enum InfoToString {
    //On Find Fire Ruin
    TIERONE("Infuser", 1),
    //On craft 4 crystals
    TIERTWO("Rift", 2),
    //On travel to fire dimension
    TIERTHREE("Inversion Serum", 3),
    //On Create warp stone
    TIERFOUR("Visceon", 4),
    //TODO On travel to florus
    TIERFIVE("Florus", 5),
    //TODO on find abandonned ship
    TIERSIX("The Dead White", 6),
    //TODO On craft Florus ingot
    TIERSEVEN("Floric Weaponry", 7),
    //TODO On craft final tier weapon
    TIEREIGHT("Fire Ritual", 8);
	
    public final int infoLevel;
    public final String powerTier;

    InfoToString(String name, int infoLevel) {
    	this.infoLevel = infoLevel;
	    this.powerTier = name;
    }
    public static String getInfo(int infoLevel) {
    	switch (infoLevel) {
    		case 1:
    			return TIERONE.powerTier;
    		case 2:
    			return TIERTWO.powerTier;
    		case 3:
    			return TIERTHREE.powerTier;
    		case 4:
    			return TIERFOUR.powerTier;
    		case 5:
    			return TIERFIVE.powerTier;
    		case 6:
    			return TIERSIX.powerTier;
    		case 7:
    			return TIERSEVEN.powerTier;
    		case 8:
    			return TIEREIGHT.powerTier;
    	}
		return "Null";	    	
	}
}
