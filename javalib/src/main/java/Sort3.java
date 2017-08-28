/**
 * Created by Administrator on 2017/8/22.
 */

public class Sort3 {
    public static int[] testarray;

    static {
        int size = 20000;
        testarray = new int[size];
        for (int i = 0; i < size; i++) {
            testarray[i] = i;


        }
    }

    public static void main(String args[]) {
        int[] array = new int[]{3, 2, 9, 1, 7, 5, 0, 4, 6, 8};
//        popSort(array);
//        selectSort(array);
//        insertSort(array);
//        quickSort(array);
//        heapSort(array);
//        mergeSort(array);
        print(array);
        print(array);
//        testSelectSort();
    }

    public static void shellSort(int[] array) {
        int gap = array.length / 2;
        while (gap != 0) {
            shellSortGap(array, gap);
            gap = gap / 2;
        }

    }

    private static void shellSortGap(int[] array, int gap) {
        for (int i = 0; i < gap; i++) {
            for (int j = i + gap; j < array.length; j = j + gap) {
                for (int k = j; k - gap >= i; k = k - gap) {
                    if (array[k] < array[k - gap]) {
                        swap(array, k, k - gap);
                    } else {
                        break;
                    }
                }
            }
        }
//        for (int i = 1; i < array.length; i++) {
//            for (int j = i; j >= 0; j--) {
//                if (array[j] < array[j - 1]) {
//                    swap(array, j, j - 1);
//                } else {
//                    break;
//                }
//            }
//
//        }
    }

    public static void mergeSort(int[] array) {
        int perLength = 2;
        while (perLength < 2 * array.length) {
            for (int groupIndex = 0; groupIndex < (array.length / perLength) + 1; groupIndex++) {
                merge(array, groupIndex, perLength);
            }

            perLength = perLength * 2;
        }

    }

    private static void merge(int[] array, int groupIndex, int perLength) {
        int f1 = groupIndex * perLength;
        int t1 = f1 + (perLength / 2) - 1;
        int f2 = t1 + 1;
        int t2 = f2 + (perLength / 2) - 1;

        if (f1 > array.length - 1) {
            return;
        }
        int[] result;
        if (t2 >= array.length) {
            result = new int[array.length - f1];
        } else {
            result = new int[perLength];
        }

        int begin1 = f1;
        int begin2 = f2;
        for (int i = 0; i < result.length; i++) {
            if (begin1 < array.length && begin1 <= t1) {
                if (begin2 < array.length && begin2 <= t2) {
                    if (array[begin1] <= array[begin2]) {
                        result[i] = array[begin1];
                        begin1++;
                    } else {
                        result[i] = array[begin2];
                        begin2++;
                    }
                } else {
                    result[i] = array[begin1];
                    begin1++;
                }
            } else {
                if (begin2 < array.length && begin2 <= t2) {
                    result[i] = array[begin2];
                    begin2++;
                } else {
                    break;
                }

            }

        }
//        System.out.println("");

        for (int i = 0; i < result.length; i++) {
            array[groupIndex * perLength + i] = result[i];
        }
    }

    public static void heapSort(int[] array) {

        int nodeSize = (array.length - 2) / 2;
        for (int i = nodeSize; i >= 0; i--) {
            buildHeap(array, i, array.length - 1);

        }
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            buildHeap(array, 0, i - 1);
        }

    }

    private static void buildHeap(int[] array, int i, int endIndex) {

        int leftIndex = i * 2 + 1;
        int rightIndex = i * 2 + 2;
        if (i >= endIndex || leftIndex > endIndex) {
            return;
        }
        boolean change = false;
        if (rightIndex <= endIndex && array[leftIndex] > array[rightIndex] || rightIndex > endIndex) {
            if (array[leftIndex] > array[i]) {
                swap(array, i, leftIndex);
                buildHeap(array, leftIndex, endIndex);
            }
        } else {
            if (array[rightIndex] > array[i]) {
                swap(array, i, rightIndex);
                buildHeap(array, rightIndex, endIndex);
            }

        }

    }


    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }

        }

    }

    public static void quickSort(int[] array) {

        quickSort1(array, 0, array.length - 1);

    }

    private static void quickSort1(int[] array, int fromIndex, int toIndex) {
        if (fromIndex >= toIndex) {
            return;
        }
        int mid = fromIndex;
        int beginIdex = fromIndex;
        int endIndex = toIndex;
        while (fromIndex < toIndex) {
            while (fromIndex < toIndex) {
                if (array[mid] > array[toIndex]) {
                    swap(array, mid, toIndex);
                    mid = toIndex;
                    break;
                } else {
                    toIndex = toIndex - 1;

                }
            }
            while (fromIndex < toIndex) {
                if (array[mid] < array[fromIndex]) {
                    swap(array, mid, fromIndex);
                    mid = fromIndex;
                    break;
                } else {
                    fromIndex = fromIndex + 1;

                }
            }
        }

        quickSort1(array, beginIdex, mid - 1);
        quickSort1(array, mid + 1, endIndex);


    }

    public static void popSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j + 1 <= i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }

    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }

            }


        }
    }

    @org.junit.Test
    public void testMergeSort() {

        reset();
        long current = System.currentTimeMillis();
        mergeSort(testarray);
        long wast = System.currentTimeMillis() - current;
        System.out.println("wast" + wast);
        boolean suc = isSorted(testarray);
        org.junit.Assert.assertTrue(suc);
        print(testarray);
    }

    @org.junit.Test
    public void testShellSort() {
        reset();
        long current = System.currentTimeMillis();
        int[] array = new int[]{3, 2, 9, 1, 7, 5, 0, 4, 6, 8};
        shellSort(testarray);
        long wast = System.currentTimeMillis() - current;
        System.out.println("wast" + wast);
        boolean suc = isSorted(testarray);
        org.junit.Assert.assertTrue(suc);
        print(testarray);
    }

    @org.junit.Test
    public void testHeapSort() {
        reset();
        long current = System.currentTimeMillis();
        heapSort(testarray);
        long wast = System.currentTimeMillis() - current;
        System.out.println("wast" + wast);
        boolean suc = isSorted(testarray);
        org.junit.Assert.assertTrue(suc);
        print(testarray);
    }

    @org.junit.Test
    public void testQuickSort() {
        reset();
        long current = System.currentTimeMillis();
        quickSort(testarray);
        long wast = System.currentTimeMillis() - current;
        System.out.println("wast" + wast);
        boolean suc = isSorted(testarray);
        org.junit.Assert.assertTrue(suc);
        print(testarray);
    }

    @org.junit.Test
    public void testInsertSort() {
        reset();
        long current = System.currentTimeMillis();
        insertSort(testarray);
        long wast = System.currentTimeMillis() - current;
        System.out.println("wast" + wast);
        boolean suc = isSorted(testarray);
        org.junit.Assert.assertTrue(suc);

//        assert suc;
    }

    @org.junit.Test
    public void testSelectSort() {
//        popSort(testarray);
        reset();
        long current = System.currentTimeMillis();
        selectSort(testarray);
        long wast = System.currentTimeMillis() - current;
        System.out.println("wast" + wast);
        boolean suc = isSorted(testarray);

        org.junit.Assert.assertTrue(suc);

    }

    private void reset() {
        for (int i = 0; i < testarray.length; i++) {
            int targetIndex = (int) (Math.random() * testarray.length);
            swap(testarray, i, targetIndex);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            if (array[i] > array[i + 1]) {
                return false;
            }

        }
        return true;

    }

    public static void print(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i == array.length - 1) {

            } else {
                System.out.print(",");
            }

        }
        System.out.println("]");
    }
}
