package com.rebootcodes.fractalclient.modules;

import com.rebootcodes.fractalclient.FractalClient;
import com.rebootcodes.fractalclient.services.eventbus.IEvent;
import com.rebootcodes.fractalclient.services.eventbus.IListener;
import com.rebootcodes.fractalclient.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.rebootcodes.fractalclient.FractalClient.EVENT_BUS;

public class Module implements IListener {
    public final String name;
    public final String title;
    public final Category category;
    public final String description;
    private Boolean active;
    public Boolean runOutOfGame = false;
    private final Logger LOG;

    public Module(String name, String title, Category category, String description) {
        this.name = name;
        this.title = title;
        this.category = category;
        this.description = description;
        this.LOG = LogManager.getLogger(FractalClient.NAME + "/Module/" + this.name);
    }

    public boolean isActive() {
        return this.active;
    }

    public void toggle() {
        if (this.active) {
            if (this.runOutOfGame || Utils.canUpdate()) {
                // Remove from active module registry.
                EVENT_BUS.unsubscribe(this);
                this.onDeactivate();
            }
            this.active = false;
        } else {
            if (this.runOutOfGame || Utils.canUpdate()) {
                // Add to active module registry.
                EVENT_BUS.subscribe(this);
                this.onActivate();
            }
            this.active = true;
        }
    }

    public void setActive(boolean state) {
        if ((!state && this.active) || (state && !this.active)) this.toggle();
    };

    public void onActivate() {
        LOG.info(this.name + " activated!");
    }
    public void onDeactivate() {
        LOG.info(this.name + " deactivated!");
    }

    @Override
    public void onEvent(IEvent event) {}
}
