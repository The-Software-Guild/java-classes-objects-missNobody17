public class SummativeSums {
    public static void main(String[] args) {  
        //Initialize array  
        int [] arr = new int [] {1, 90, -33, -55, 67, -16, 28, -55, 15 };  
        int [] arr2 = new int [] {999, -60, -77, 14, 160, 301};  
        int [] arr3 = new int [] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};  
        int sum = addArray(arr);
        int sum2 = addArray(arr2);
        int sum3 = addArray(arr3);
        System.out.println("Array Sum: " + sum);  
        System.out.println("Array Sum: " + sum2); 
        System.out.println("Array Sum: " + sum3); 
    }  

    static int addArray(int [] arr) {
        int sum = 0;  
        //Loop through the array to calculate sum of elements  
        for (int i = 0; i < arr.length; i++) {  
           sum = sum + arr[i];  
        }  
        return sum;
    }
}
