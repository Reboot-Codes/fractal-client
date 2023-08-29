package com.rebootcodes.fractalclient.services.eventbus;

public class EventBus implements IEventBus {

    @Override
    public IEvent post(IEvent event) {
        return null;
    }

    @Override
    public ICancellable post(ICancellable event) {
        return null;
    }

    @Override
    public void subscribe(IListener subscriber) {

    }

    @Override
    public void unsubscribe(IListener subscriber) {

    }
}
