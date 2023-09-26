package algorithm;

import java.util.Arrays;

/*
* 基数排序（桶排序）
* */
public class RadixSORT {


    public static void main(String[] args) {
        int[] arr = {13,18,444,4445,87894,4465,87878974,5646546,4654,44};
        radixSort(arr);
    }

    public static  void radixSort(int[] arr){
        // 1》 得到数组中最大的数
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //2> 得到最大的数是几位数
        int maxLength = (max + "").length();

        // 3> 定义一个2维数组 表示10个桶 每个桶表示一个二维数组 注意 大小定义为传过来数组的最大值 防止数组溢出
        int[][] bucket = new int[10][arr.length];
        //

        //4> 为记录每个桶中实际有效存放了多少数据，我们定义一个一维数组来记录各个桶中存放的数据
        int[] bucketElementCounts = new int[10];

        //5> 利用循环来处理数据
        for (int i = 0, n=1; i < maxLength; i++, n*=10) {
            // 6> 针对每个元素对应位进行排序处理
            for (int j = 0; j < arr.length; j++) {
                int dightOfEelement = arr[j]/n%10;
                // 7> 放入对应的桶中
                bucket[dightOfEelement][bucketElementCounts[dightOfEelement]] = arr[j];
                bucketElementCounts[dightOfEelement]++;
            }
            //8> 按照这个桶的顺序 依次取出放入原来的数组中
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //9> 如果桶中有数据，我们放到原数组中
                if (bucketElementCounts[k]!=0){
                    // 10> 循环该桶即第k个一维数组  中放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 11> 取出元素放入到arr中
                        arr[index++] = bucket[k][l];
                    }
                }

                // 12> 第i+1轮结束后 需要将每个桶中的数据清除
                bucketElementCounts[k] = 0;
                //
            }



        }

        System.out.println("排序后的数组为： " + Arrays.toString(arr));

    }
}
