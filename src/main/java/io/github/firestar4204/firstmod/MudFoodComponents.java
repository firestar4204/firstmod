package io.github.firestar4204.firstmod;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class MudFoodComponents {
    
    public static final FoodComponent DRIED_MUD_BALL = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.35F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 0), 0.75F).snack().build();
}
