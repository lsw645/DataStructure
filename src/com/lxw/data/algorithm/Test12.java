package com.lxw.data.algorithm;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2018/09/06
 *     desc   :
 * </pre>
 */

class Test12 {
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        Test12 test12 = new Test12();
        test12.test();
    }

    private void test() {
        for (int j = 0; j < 20; j++) {
            Thread thread = new Thread(mRunnable);
            thread.start();
        }
    }


    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (flag) {
                flag = false;
                System.out.println("sssss" + flag);
            }
        }
    };
}
