package earth.terrarium.adastra.common.entities.mob;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.level.Level;

// LEGACY ENTITY. WILL BE REPLACED IN THE FUTURE.
public class ZombifiedMogler extends Zoglin {

    public ZombifiedMogler(EntityType<? extends Zoglin> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 40.0)
            .add(Attributes.MOVEMENT_SPEED, 0.3f)
            .add(Attributes.KNOCKBACK_RESISTANCE, 0.6f)
            .add(Attributes.ATTACK_KNOCKBACK, 1.0)
            .add(Attributes.ATTACK_DAMAGE, 6.0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceSquared) {
        return false;
    }
}
