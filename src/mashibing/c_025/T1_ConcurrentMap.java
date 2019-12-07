package mashibing.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class T1_ConcurrentMap {
//    Map map = new ConcurrentHashMap();
//    Map map = new ConcurrentSkipListMap();
//    Map map = new Hashtable();
    Map map = Collections.synchronizedMap(new HashMap<>());

}
