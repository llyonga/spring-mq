package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by 小五儿 on 2017-08-04
 */
public class Demo1 {

    public static void main(String[] args) {

        method2();

    }

    public static void method2() {
        int[] arr = new int[]{2,2,2,2,2,2,2,2};
        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();

        for (int i=0,length = arr.length;i<length;i++) {
            arr[i] = arr[i]<<2;
        }
        System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("总耗时1："+(end-start));
    }


    public static void method1() {
        ArrayList list = new ArrayList();

        /*for (int i = 0; i < 100000; i++) {
            list.add(new Random().nextInt(100000));
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end-start));


        start = System.currentTimeMillis();
        for (int i = 0,length = list.size();i<length ;i++){
            list.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("优化后总耗时："+(end-start));*/


        int[] arr = new int[10000000];
        long start = System.currentTimeMillis();
        /*for (int i =0;i<10;i++) {
            try {
                System.out.println(list.get(0));
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/

        for (int i=0,length = arr.length;i<length;i++) {
            arr[i] = arr[i]*4;
        }

        long end = System.currentTimeMillis();
        System.out.println("总耗时1："+(end-start));

        start = System.currentTimeMillis();
        /*try {
            for (int i =0;i<10;i++) {
                System.out.println(list.get(0));
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
        for (int i=0,length = arr.length;i<length;i++) {
            arr[i] = arr[i]<<2;
        }
        end = System.currentTimeMillis();
        System.out.println("总耗时2："+(end-start));
    }

}
