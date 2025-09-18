package ink.abalone.week2;

public class Sort {
    void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }
    void insertSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // 更新最小元素索引
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    public static void main(String[] args) {
        Sort s = new Sort();
        int[] array = {9,23,4,125,6,61,754,568,8,34,423};
        int[] array2 = {9,23,4,125,6,61,754,568,8,34,423};
        s.bubbleSort(array);
        s.insertSort(array2);
        for (int j : array) System.out.print(j + " ");
        System.out.println();
        for (int j : array2) System.out.print(j + " ");
    }
}
