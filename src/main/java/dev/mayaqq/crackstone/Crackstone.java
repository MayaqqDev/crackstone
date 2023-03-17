package dev.mayaqq.crackstone;

import dev.mayaqq.crackstone.config.CrackstoneConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Crackstone implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Crackstone");

    public static final TagKey<Block> CRACKABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier("crackstone", "crackable"));
    @Override
    public void onInitialize() {
        CrackstoneConfig.load();
    }
}
