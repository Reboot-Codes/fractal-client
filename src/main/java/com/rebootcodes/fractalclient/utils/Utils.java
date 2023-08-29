package com.rebootcodes.fractalclient.utils;

import static com.rebootcodes.fractalclient.FractalClient.mc;

public class Utils {
    public static boolean canUpdate() {
        return mc != null && mc.world != null && mc.player != null;
    }
}
