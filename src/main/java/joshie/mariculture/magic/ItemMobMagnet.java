package joshie.mariculture.magic;

import java.util.List;

import joshie.mariculture.api.core.MaricultureTab;
import joshie.mariculture.core.handlers.LogHandler;
import joshie.mariculture.core.items.ItemMCDamageable;
import joshie.mariculture.core.util.MCTranslate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

/** Mob Magnet, On Right Click, Teleports all mobs within 64 x 64 blocks around the player of the type to an area near you
 * You must first kill a mob of the type you want first, in order to teleport them all to you **/
public class ItemMobMagnet extends ItemMCDamageable {
    public ItemMobMagnet(int dmg) {
        super(dmg);
        setCreativeTab(MaricultureTab.tabMagic);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        if (!stack.hasTagCompound()) {
            list.add(MCTranslate.translate("noBound1"));
            list.add(MCTranslate.translate("noBound2"));
            return;
        } else {
            list.add(MCTranslate.translate("bound"));
            list.add(stack.stackTagCompound.getString("MobName"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) return stack;

        try {
            Entity entity = EntityList.createEntityByName(stack.stackTagCompound.getString("MobName"), world);
            List<EntityMob> enemies = world.getEntitiesWithinAABB(entity.getClass(), player.boundingBox.expand(32D, 32D, 32D));
            int x = (int) player.posX;
            int y = (int) (player.posY + 1);
            int z = (int) player.posZ;
            world.playSoundEffect(x, y, z, "mob.endermen.portal", 1.0F, 1.0F);
            for (Object i : enemies)
                if (i instanceof EntityLivingBase) {
                    ((EntityLivingBase) i).setPositionAndUpdate(x, y, z);
                    if (stack.attemptDamageItem(1, world.rand)) {
                        stack.stackSize--;
                    }
                }
        } catch (Exception e) {
            LogHandler.log(Level.WARN, "Mob Magnet Failed to find class for the target entities!");
        }

        return stack;
    }
}
