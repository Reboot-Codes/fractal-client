package com.rebootcodes.fractalclient.services.modules.categories;

import com.rebootcodes.fractalclient.modules.Category;
import com.rebootcodes.fractalclient.utils.Color;
import net.minecraft.item.Items;

public class Movement extends Category {
    public Movement() {
        super("movement", "Movement", Items.DIAMOND_BOOTS.getDefaultStack(), new Color(50, 233, 202));
    }
}
