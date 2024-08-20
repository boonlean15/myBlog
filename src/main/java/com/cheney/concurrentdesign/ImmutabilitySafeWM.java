package com.cheney.concurrentdesign;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 合理库存的原子化实现
 */
public class ImmutabilitySafeWM {

    class SafeWM{
        final int upper;
        final int low;
        SafeWM(int upper,int low){
            this.low = low;
            this.upper = upper;
        }
    }

    final AtomicReference<SafeWM> rf = new AtomicReference<>(new SafeWM(0,0));

    void setUpper(int upper){
        while (true){
            SafeWM safeWM = rf.get();
            if(upper < safeWM.low){
                throw new RuntimeException("set upper cant not less low");
            }
            SafeWM newSafe = new SafeWM(upper,safeWM.low);
            if(rf.compareAndSet(safeWM,newSafe)){
                return;
            }
        }
    }

}
