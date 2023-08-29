package com.rebootcodes.fractalclient.services.modules.categories;

import com.rebootcodes.fractalclient.modules.Category;
import com.rebootcodes.fractalclient.utils.Color;
import net.minecraft.item.Items;

public class Misc extends Category {
    public Misc() {
        super("misc", "Misc", Items.COAL.getDefaultStack(), new Color(50, 233, 202));
    }
}
