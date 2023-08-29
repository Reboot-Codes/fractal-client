package com.rebootcodes.fractalclient.services.modules.categories;

import com.rebootcodes.fractalclient.modules.Category;
import com.rebootcodes.fractalclient.utils.Color;
import net.minecraft.item.Items;

public class Network extends Category {
    public Network() {
        super("network", "Network", Items.SCULK_SENSOR.getDefaultStack(), new Color(255,255,0));
    }
}
