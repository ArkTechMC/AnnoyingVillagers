package com.iafenvoy.annoyingvillagers;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModEntities;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItems;

import com.iafenvoy.annoyingvillagers.registry.AnnoyingModSounds;
import com.iafenvoy.annoyingvillagers.registry.AnnoyingModItemGroups;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("annoying_villagers")
public class AnnoyingMod {
	public static final Logger LOGGER = LogManager.getLogger(AnnoyingMod.class);
	public static final String MODID = "annoying_villagers";

	public AnnoyingMod() {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus modbus = FMLJavaModLoadingContext.get() .getModEventBus();
		AnnoyingModItems.ITEMS.register(modbus);
		AnnoyingModItemGroups.REGISTRY.register(modbus);
		AnnoyingModSounds.REGISTRY.register(bus);
		AnnoyingModEntities.REGISTRY.register(bus);
		AnnoyingModItems.REGISTRY.register(modbus);
	}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new Identifier(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketByteBuf> encoder, Function<PacketByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}
