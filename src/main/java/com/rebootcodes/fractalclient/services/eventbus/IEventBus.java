package com.rebootcodes.fractalclient.services.eventbus;

public interface IEventBus {
    IEvent post(IEvent event);
    ICancellable post(ICancellable event);
    void subscribe(IListener subscriber);
    void unsubscribe(IListener subscriber);
}
