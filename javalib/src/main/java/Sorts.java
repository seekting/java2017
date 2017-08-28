/**
 * Created by seekting on 17-5-3.
 */

public class Sorts {

    public static void main(String[] args) {
        int[] a = new int[]{9, 8, 7,  14, 12, 13, 18, 15, 120, 111, 129};
        quickSort(a, 0, a.length - 1);
//        radixSort(a, 3);
//        Sorts.merge(a);
//        mergeSort(a);
        print(a);
    }

    public static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");

        }
        System.out.println();
    }


    public static void bubbleSort(int[] a) {

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j + 1 <= i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

    }

    public static void selectSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;

                }

            }
            if (minIndex != i) {
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }

    public static void insertSort(int[] a) {

        for (int i = 1; i < a.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } else {
                    break;
                }

            }

        }
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int key = a[left];

        while (i < j) {
            while (i < j && a[j] > key) {

                j--;
            }
            a[i] = a[j];
            while (i < j && a[i] <= key) {
                i++;

            }
            a[j] = a[i];

        }
        a[i] = key;
        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);


    }

    /**
     * 建大堆(大的在最上)
     */
    private static void buildMaxHeap(int[] a, int parentIndex, int length) {
        int left = parentIndex * 2 + 1;
        int largest = parentIndex;
        int right = parentIndex * 2 + 2;
        if (left < length) {
            if (a[left] > a[largest]) {
                largest = left;
            }


        }
        if (right < length) {
            if (a[right] > a[largest]) {
                largest = right;
            }
        }
        if (largest != parentIndex) {
            int temp = a[parentIndex];
            a[parentIndex] = a[largest];
            a[largest] = temp;
            buildMaxHeap(a, largest, length);
        }


    }

    public static void heapSort(int[] a) {
        for (int i = a.length - 1 / 2; i >= 0; i--) {
            buildMaxHeap(a, i, a.length);
        }
        for (int i = a.length - 1; i > 0; i--) {
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            buildMaxHeap(a, 0, i);
        }

    }

    /**
     * 基排序
     *
     * @param a
     */
    public static void radixSort(int[] a, int max) {
        int[][] pool = new int[10][a.length];
        int mod = 10;
        int[] indexs = new int[10];
        int index = 0;
        int current = 1;

        while (current <= max) {
            for (int i = 0; i < a.length; i++) {
                int j = (a[i] % mod) / (mod / 10);
                pool[j][indexs[j]] = a[i];
                indexs[j]++;

            }
            for (int i = 0; i < indexs.length; i++) {
                if (indexs[i] != 0) {
                    for (int j = 0; j < indexs[i]; j++) {
                        a[index] = pool[i][j];
                        index++;

                    }
                }
                indexs[i] = 0;


            }
            mod = 10 * mod;
            current++;
            index = 0;
        }


    }

    /**
     * 希尔排序
     *
     * @param a
     */
    public static void shellSort(int[] a) {
        int d = a.length / 2;
//        d=5 分五组每组两个数
//        d=2　分两组每组五个数
//        d=1 分一组，每组十个数
        while (d != 0) {
//            第x和第x+d和x+d+d比
            for (int x = 0; x < d; x++) {
                for (int j = x; j + d < a.length; j = j + d) {
                    for (int k = j + d; k - d >= x; k = k - d) {
                        if (a[k - d] > a[k]) {
                            int temp = a[k - d];
                            a[k - d] = a[k];
                            a[k] = temp;
                        }

                    }
                }

            }


            d = d / 2;
        }


    }

    public static void mergeSort(int[] a) {
        for (int gap = 1; gap < a.length; gap = gap * 2) {
            mergePass(a, gap);
        }


    }

    private static void mergePass(int[] a, int gap) {
//        合并相邻两个表
        for (int i = 0; i < a.length; i = i + 2 * gap) {
//            第一组: a[i] - a[i + gap - 1];
//            第二组: a[i+gap]-a[i+2*gap-1];
            int nT = i + 2 * gap - 1;
            int nF = i + gap;
            int mF = i;
            int mT = i + gap - 1;
            if (nF >= a.length) {
                continue;
            }
            if (nT >= a.length) {
                nT = a.length - 1;
            }
            merge(a, mF, mT, nF, nT);

        }

    }

    public static void merge(int[] a, int mF, int mT, int nF, int nT) {
        int[] array = new int[nT - mF + 1];
        int begin = mF;
        for (int i = 0; i < array.length; i++) {
            if (mF <= mT && (nF > nT || a[mF] <= a[nF])) {
                array[i] = a[mF];
                mF++;
            } else {
                if (nF <= nT || mF > mT) {
                    array[i] = a[nF];
                    nF++;
                }

            }

        }
        arrayCopy(a, begin, array, 0, array.length);


    }

    private static void arrayCopy(int[] a, int begin, int[] b, int from, int to) {
        for (int i = 0; i < to; i++) {
            a[begin] = b[i];
            begin++;

        }
    }

    public static int[] merge(int[] m, int[] n) {
        int[] result = new int[m.length + n.length];
        int mIndex = 0;
        int nIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (m[mIndex] <= n[nIndex]) {
                result[i] = m[mIndex];
                mIndex++;

            } else {
                result[i] = m[nIndex];
                nIndex++;
            }

        }
        return result;


    }
}
