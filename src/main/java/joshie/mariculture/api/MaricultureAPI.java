package joshie.mariculture.api;

import joshie.mariculture.api.aquaculture.Aquaculture;
import joshie.mariculture.api.diving.Diving;
import joshie.mariculture.api.fishing.Fishing;
import joshie.mariculture.api.gen.WorldGen;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class MaricultureAPI {
    public static final Logger logger = LogManager.getLogger("Mariculture-API");

    /** Before calling these make sure that
     *  the relevant modules are enabled first,
     *  or these will remain as null **/

    public static Aquaculture aquaculture = null;
    public static Diving diving = null;
    public static Fishing fishing = null;
    public static WorldGen worldGen = null;

    /** Use this helped method to determine if a module is enabled
     *  It's probably a good idea to cache this value for yourself **/
    public static boolean isModuleEnabled(String name) {
        try {
            return (Boolean) Class.forName("joshie.mariculture.modules.ModuleManager").getMethod("isModuleEnabled", String.class).invoke(null, name);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.log(Level.ERROR, "Could not find the isModuleEnabled method");
        }

        //Default Return
        return false;
    }
}
