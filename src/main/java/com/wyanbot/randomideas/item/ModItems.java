package com.wyanbot.randomideas.item;

import com.wyanbot.randomideas.RandomIdeas;
import com.wyanbot.randomideas.item.custom.UnnamedScepterItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RandomIdeas.MOD_ID);

    public static final RegistryObject<Item> UNNAMED_SCEPTER = ITEMS.register("unnamed_scepter",
            () -> new UnnamedScepterItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
