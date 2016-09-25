package main.java;

import java.util.EventListener;

/**
 * Created by arsen on 21.09.2016.
 */
public interface structGameChangedListener extends EventListener {
  public  void call(structGameChangedEvent e);
}

