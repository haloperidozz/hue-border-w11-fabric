package com.github.haloperidozz.hueborder;

import com.github.haloperidozz.hueborder.win32.version.WinVersion;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HueBorder implements ModInitializer {
	public static final String MOD_ID = "hue-border";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		if (!WinVersion.isWindows11Build22000OrGreater()) {
			LOGGER.error("unsupported system: Windows 11 (build >22000O) is required");
		}
	}
}