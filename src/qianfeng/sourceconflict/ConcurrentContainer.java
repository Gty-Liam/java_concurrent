package qianfeng.sourceconflict;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 卖票
 */
public class ConcurrentContainer {
    Map hashMap = Collections.synchronizedMap(new HashMap());
    static Queue list = new ConcurrentLinkedQueue<>();

}
