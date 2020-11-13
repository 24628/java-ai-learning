package src.helper;

import java.lang.ref.WeakReference;

public class GarbishColleter {

    /**
     * This method guarantees that garbage collection is
     * done unlike <code>{@link System#gc()}</code>
     * https://stackoverflow.com/questions/1481178/how-to-force-garbage-collection-in-java
     * GenerateList(int pixelX, int pixelY) will cause memory leak if this is not here
     */
    public static void cleanUp() {
        Object obj = new Object();
        WeakReference ref = new WeakReference<Object>(obj);
        obj = null;
        while (ref.get() != null) {
            System.gc();
        }
    }
}

