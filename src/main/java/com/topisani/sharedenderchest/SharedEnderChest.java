package com.topisani.sharedenderchest;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;

public class SharedEnderChest implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		System.out.println("Initialized SharedEnderChest!");
	}
}
