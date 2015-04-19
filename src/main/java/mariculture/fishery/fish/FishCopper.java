package mariculture.fishery.fish;

import static mariculture.core.lib.MCLib.dropletCopper;
import static mariculture.core.lib.MCLib.dropletEarth;
import mariculture.api.core.Environment.Salinity;
import mariculture.api.fishery.RodType;
import mariculture.api.fishery.fish.FishSpecies;

public class FishCopper extends FishSpecies {
    @Override
    public int getTemperatureBase() {
        return 15;
    }

    @Override
    public int getTemperatureTolerance() {
        return 8;
    }

    @Override
    public Salinity getSalinityBase() {
        return Salinity.FRESH;
    }

    @Override
    public int getSalinityTolerance() {
        return 1;
    }

    @Override
    public boolean isDominant() {
        return true;
    }

    @Override
    public int getLifeSpan() {
        return 7;
    }

    @Override
    public int getFertility() {
        return 70;
    }

    @Override
    public void addFishProducts() {
        addProduct(dropletCopper, 10D);
        addProduct(dropletEarth, 7D);
    }

    @Override
    public RodType getRodNeeded() {
        return RodType.GOOD;
    }
}
