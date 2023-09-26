import java.util.Arrays;

public class productionSparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组
        int[][] chessArray = new int[11][11];
        chessArray[1][2] =1;
        chessArray[2][3] =2;
        chessArray[4][5] =2;

        // 输出原始的二位数组
        System.out.println("输出原始的数组");
        Arrays.stream(chessArray).forEach(r-> {
            Arrays.stream(r).forEach(r2-> System.out.printf("%d\t",r2));
            System.out.println();
        });


        // 提前设置一个数组用于提前填充有效数据最后将其对拷到稀疏数组中
        int [][] cpArray = new int[chessArray.length][3];

        // 将二位数组转换为稀疏数组
        int sum =0;  //用作累加一共有几个有效值

        for (int i = 0; i < chessArray.length; i++) {
            for (int i1 = 0; i1 < chessArray[i].length; i1++) {
                if (chessArray[i][i1] !=0){
                    // 将值填充到对拷数组中
                    cpArray[sum][0] = i;    //行
                    cpArray[sum][1] = i1;   //列
                    cpArray[sum][2] = chessArray[i][i1];    //值
                    sum++;  // 值不为0 累加器+1
                }
            }
        }

        // 输出对拷数组
        System.out.println("输出对拷的数组");
        Arrays.stream(cpArray).forEach(r-> {
            Arrays.stream(r).forEach(r2-> System.out.printf("%d\t",r2));
            System.out.println();
        });


        // 创建稀疏数组
        int[][] sparseArray = new int[sum+1][3];
        //稀疏队列初识化
        sparseArray[0][0] = chessArray.length;  // 行
        sparseArray[0][1] = chessArray[0].length;  // 列
        sparseArray[0][2] = sum;    // 累加的有效值
        //对拷数组数据和稀疏队列的对拷
        for (int i = 1; i <= sum; i++) {
            System.arraycopy(cpArray[i - 1], 0, sparseArray[i], 0, 3);
        }

        // 输出稀疏数组
        System.out.println("输出稀疏数组");
        Arrays.stream(sparseArray).forEach(r-> {
            Arrays.stream(r).forEach(r2-> System.out.printf("%d\t",r2));
            System.out.println();
        });


    }
}
