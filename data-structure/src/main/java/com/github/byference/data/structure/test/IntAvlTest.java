package com.github.byference.data.structure.test;

import com.github.byference.data.structure.core.tree.IntAvl;
import org.junit.Test;

/**
 * IntAvlTest
 *
 * @author byference
 * @since 2019-08-17
 */
public class IntAvlTest {



    @Test
    public void testDelete5() {

        IntAvl avl = new IntAvl();

        avl.put(5);
        avl.put(3);
        avl.put(1);
        avl.put(2);
        avl.put(8);
        avl.put(9);
        avl.put(6);
        avl.put(14);
        avl.put(17);
        avl.put(20);

        // 删除 8(root)
        avl.delete(8);

        System.out.println(avl);
    }


    /**
     *    4                         4
     *   / \                       / \
     *  3   7    ->  del(7)  ->   3   6
     *     / \                        \
     *    6   8                        8
     */
    @Test
    public void testDelete4() {

        IntAvl avl = new IntAvl();

        avl.put(4);
        avl.put(3);
        avl.put(7);
        avl.put(6);
        avl.put(8);

        // 删除 7
        avl.delete(7);

        System.out.println(avl);
    }


    /**
     *      4                      4
     *     / \                    / \
     *    2   8  ->  del(2) ->   3   8
     *   / \                    /
     *  1   3                  1
     */
    @Test
    public void testDelete3() {

        IntAvl avl = new IntAvl();

        avl.put(4);
        avl.put(2);
        avl.put(8);
        avl.put(1);
        avl.put(3);

        // 删除 2
        avl.delete(2);

        System.out.println(avl);
    }


    /**
     *    4                         7
     *   / \                       / \
     *  3   7    ->  del(3)  ->   4   8
     *     / \                     \
     *    6   8                     6
     */
    @Test
    public void testDelete2() {

        IntAvl avl = new IntAvl();

        avl.put(4);
        avl.put(3);
        avl.put(7);
        avl.put(6);
        avl.put(8);

        // 删除 3
        avl.delete(3);

        System.out.println(avl);
    }


    /**
     *      4                      2
     *     / \                    / \
     *    2   8  ->  del(8) ->   1   4
     *   / \                        /
     *  1   3                      3
     */
    @Test
    public void testDelete1() {

        IntAvl avl = new IntAvl();

        avl.put(4);
        avl.put(2);
        avl.put(8);
        avl.put(1);
        avl.put(3);

        // 删除 8
        avl.delete(8);

        System.out.println(avl);
    }


    @Test
    public void testPut() {

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
