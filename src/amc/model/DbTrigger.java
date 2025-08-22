package amc.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DbTrigger<T> {
    private class WeakListener extends WeakReference<Runnable> {
        public WeakListener(Runnable referent) { super(referent); }
    }

    private final List<WeakListener> listenerLs = new ArrayList<> ();

    public DbTrigger() {}

    public void register(Runnable handler) {
        listenerLs.add(new WeakListener(handler));
    }

    public void fire() {
        for (WeakListener listener: listenerLs) {
            if (listener.get() == null) continue;
            listener.get().run();
        }
        listenerLs.removeIf(listener -> listener.get() == null);
    }
}
