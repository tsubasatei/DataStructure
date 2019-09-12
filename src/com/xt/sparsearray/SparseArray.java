package com.xt.sparsearray;

/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        /**
         * 创建一个原始的二维数组 11*11
         * 0：表示没有棋子， 1：表示黑子， 2：表示蓝子
         */
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        System.out.println("原始的二维数组是：");
        printArr(chessArr);

        // 将 二维数组 转 稀疏数组
        int[][] sparseArr = transferTwoDimensional2SparseArray(chessArr);
        System.out.println("二维数组转为稀疏数组是：");
        printArr(sparseArr);

        // 将 稀疏数组 恢复成 原始的 二维数组
        int[][] chessArr2 = transferSparse2TwoDimensionalArray(sparseArr);
        System.out.println("恢复的二维数组是：");
        printArr(chessArr2);

    }

    /**
     * 将 稀疏数组 恢复成 原始的 二维数组
     * @param sparseArr
     * @return
     */
    private static int[][] transferSparse2TwoDimensionalArray(int[][] sparseArr) {
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        
        // 2. 再读取稀疏数组后几行的数据（从第二行开始），并赋给 原始的二位数组 即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];

        }

        return chessArr;
    }

    /**
     * 将 二维数组 转 稀疏数组
     */
    private static int[][] transferTwoDimensional2SparseArray(int[][] chessArr) {
        // 1. 先遍历二维数组 得到非 0 数据的个数
        int sum = 0;
        for (int[] row : chessArr) {
            for (int data : row) {
                if (data != 0) {
                    sum ++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        // 3. 遍历二维数组，将非0的值存放到 sparseArr 中
        int count = 0;  // count 用于记录是第几个非0数据
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count ++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        return sparseArr;
    }

    /**
     * 打印数组
     * @param chessArr
     * @return
     */
    private static void printArr(int[][] chessArr) {
        for(int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
