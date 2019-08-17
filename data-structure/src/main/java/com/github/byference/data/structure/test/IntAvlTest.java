package com.github.byference.data.structure.test;

import com.github.byference.data.structure.core.tree.IntAvl;

/**
 * IntAvlTest
 *
 * @author byference
 * @since 2019-08-17
 */
public class IntAvlTest {

    public static void main(String[] args) {

        IntAvl avl = new IntAvl();
        // 6 4 3 7 8 9 11

        avl.put(6);
        avl.put(4);
        avl.put(3);
        avl.put(7);
        avl.put(8);
        avl.put(9);
        avl.put(11);

        System.out.println(avl);
    }

}
