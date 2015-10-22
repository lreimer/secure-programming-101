/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 QAware GmbH, Munich, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package de.qaware.campus.secpro.mlr01;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Special factory to obtain unsafe instance. Ideas and code are taken from
 * http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
 *
 * @author mario-leander.reimer
 */
public final class TheUnsafe {

    /**
     * Utility factory class. No instances.
     */
    private TheUnsafe() {
    }

    /**
     * Factory method for Unsafe instances.
     *
     * @return an Unsafe instance
     */
    public static Unsafe getInstance() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Convert object to its address in memory.
     *
     * @param obj the object
     * @return the address in memory
     */
    public static long toAddress(Object obj) {
        Object[] array = new Object[]{obj};
        long baseOffset = getInstance().arrayBaseOffset(Object[].class);
        return normalize(getInstance().getInt(array, baseOffset));
    }

    /**
     * Convert address to associated object in memory.
     *
     * @param address the address
     * @return the object
     */
    public static Object fromAddress(long address) {
        Object[] array = new Object[]{null};
        long baseOffset = getInstance().arrayBaseOffset(Object[].class);
        getInstance().putLong(array, baseOffset, address);
        return array[0];
    }

    /**
     * Obtain a copy of the given object. No Cloneable required.
     *
     * @param obj the object to copy
     * @return the copy
     */
    public static Object shallowCopy(Object obj) {
        long size = sizeOf(obj);
        long start = toAddress(obj);

        Unsafe instance = getInstance();
        long address = instance.allocateMemory(size);
        instance.copyMemory(start, address, size);
        return fromAddress(address);
    }

    /**
     * Much simpler sizeOf can be achieved if we just read size value from the class
     * struct for this object, which located with offset 12 in JVM 1.7 32 bit.
     *
     * @param object the object instance
     * @return the size of the object
     */
    public static long sizeOf(Object object) {
        Unsafe instance = getInstance();
        return instance.getAddress(normalize(instance.getInt(object, 4L)) + 12L);
    }

    /**
     * Normalize is a method for casting signed int to unsigned long, for correct address usage.
     *
     * @param value the signed int
     * @return an unsigned long
     */
    private static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }
}
