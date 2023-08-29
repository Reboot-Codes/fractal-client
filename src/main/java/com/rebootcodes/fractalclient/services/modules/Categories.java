package com.rebootcodes.fractalclient.services.modules;

import com.rebootcodes.fractalclient.modules.Category;
import com.rebootcodes.fractalclient.services.modules.categories.Movement;
import com.rebootcodes.fractalclient.services.modules.categories.Misc;
import com.rebootcodes.fractalclient.services.modules.categories.Network;

import java.util.ArrayList;
import java.util.List;

public class Categories {
    public static final Category network = new Network();
    public static final Category movement = new Movement();
    public static final Category misc = new Misc();

    public List<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(network);
        categories.add(movement);
        categories.add(misc);

        return categories;
    }
}
