package com.rebootcodes.fractalclient.services.modules;

import com.rebootcodes.fractalclient.FractalClient;
import com.rebootcodes.fractalclient.modules.Module;
import com.rebootcodes.fractalclient.modules.movement.Baritone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Modules {
    public static final Categories categories = new Categories();
    private final Map<String, Module> modules = new HashMap<>();
    private static final Logger LOG = LogManager.getLogger(FractalClient.NAME + "/Modules");
    private final Map<String, Module> activeModules = new HashMap<>();

    public Modules() {
        addModule(new Baritone());
    }

    public void addModule(Module module) {
        modules.put(module.name, module);
    }

    public void removeModule(Module module) {
        modules.put(module.name, null);
    }

    public void removeModule(String moduleName) {
        modules.put(moduleName, null);
    }

    public Map<String, Module> getModules() {
        return this.modules;
    }

    public void addActiveModule(Module module) {
        if (!this.activeModules.containsKey(module.name)) {
            module.setActive(true);
        } else {
            LOG.warn("Module \"{}\" is already active!", module.name);
        }
    }

    public void addActiveModule(String moduleName) {
        if (!this.activeModules.containsKey(moduleName)) {
            if (modules.containsKey(moduleName)) {
                Module module = modules.get(moduleName);
                module.setActive(true);
            } else {
                LOG.error("Cannot activate a module (\"{}\") that isn't in the module registry! Is it loaded?", moduleName);
            }
        }
    }

    public void removeActiveModule(Module module) {
        if (this.activeModules.containsKey(module.name)) {
            module.setActive(false);
        } else {
            LOG.warn("Module \"{}\" is already inactive!", module.name);
        }
    }

    public void removeActiveModule(String moduleName) {
        if (this.activeModules.containsKey(moduleName)) {
            Module module = modules.get(moduleName);
            module.setActive(false);
        } else {
            LOG.warn("Module \"{}\" is already inactive!", moduleName);
        }
    }

    public Map<String, Module> getActiveModules() {
        return this.activeModules;
    }
}
