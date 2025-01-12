
import java.lang.reflect.Array;
import java.util.*;

public class des {

  static int initial_perm []= {58, 50, 42, 34, 26, 18, 10, 2,
  60, 52, 44, 36, 28, 20, 12, 4,
  62, 54, 46, 38, 30, 22, 14, 6,
  64, 56, 48, 40, 32, 24, 16, 8,
  57, 49, 41, 33, 25, 17, 9, 1,
  59, 51, 43, 35, 27, 19, 11, 3,
  61, 53, 45, 37, 29, 21, 13, 5,
  63, 55, 47, 39, 31, 23, 15, 7};

  static int exp_d[] = {32, 1, 2, 3, 4, 5, 4, 5,
  6, 7, 8, 9, 8, 9, 10, 11,
  12, 13, 12, 13, 14, 15, 16, 17,
  16, 17, 18, 19, 20, 21, 20, 21,
  22, 23, 24, 25, 24, 25, 26, 27,
  28, 29, 28, 29, 30, 31, 32, 1};
  

  static int sbox[][][] = {{{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
		{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
		{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
		{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},

		{{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
		{3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
		{0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
		{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},

		{{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
		{13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
		{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
		{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},

		{{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
		{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
		{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
		{3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},

		{{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
		{14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
		{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
		{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},

		{{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
		{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
		{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
		{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},

		{{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
		{13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
		{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
		{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},

		{{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
		{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
		{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
		{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}

  };

  static int per[] = {16, 7, 20, 21,
	29, 12, 28, 17,
	1, 15, 23, 26,
	5, 18, 31, 10,
	2, 8, 24, 14,
	32, 27, 3, 9,
	19, 13, 30, 6,
	22, 11, 4, 25};
  
  static int final_perm[] = {40, 8, 48, 16, 56, 24, 64, 32,
  39, 7, 47, 15, 55, 23, 63, 31,
  38, 6, 46, 14, 54, 22, 62, 30,
  37, 5, 45, 13, 53, 21, 61, 29,
  36, 4, 44, 12, 52, 20, 60, 28,
  35, 3, 43, 11, 51, 19, 59, 27,
  34, 2, 42, 10, 50, 18, 58, 26,
  33, 1, 41, 9, 49, 17, 57, 25};

  public static String xor(String a, String b){
    StringBuilder ans = new StringBuilder();
    for(int i=0;i<a.length();i++){
        if(a.charAt(i)==b.charAt(i)){
          ans.append('0');
        }else{
          ans.append('1');
        }
    }
    return ans.toString();

  }

  public static int bin2dec(int binary){
    
    int decimal = 0;
    int i=0;
    int n=0;
    while(binary!=0){
      int dec = binary%10;
      decimal += dec*Math.pow(2, i);
      binary = binary/10;
      i +=1;
    }



    return decimal;
  }

  public static String dec2bin(int num){
    String binary = Integer.toBinaryString(num);

    
    int length = binary.length();
    if (length % 4 != 0) {
        int div = length / 4;
        int counter = (4 * (div + 1)) - length;

        StringBuilder paddedBinary = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            paddedBinary.append('0');
        }
        paddedBinary.append(binary);
        binary = paddedBinary.toString();
    }
    return binary;



  }
  public static String encrypt(String pt,String key){
    String keybin = hex2bin(key);
      String keynew = permute(keybin, keyp, 56);
      // System.out.println("bin is  "+keynew);
      String left1 = keynew.substring(0,28);
      String right1 = keynew.substring(28);
      
     
      ArrayList<String> rkb = new ArrayList<String>();
      ArrayList<String> rk = new ArrayList<String>();

      for(int i=0;i<16;i++){
          String templeft = shift_left(left1, shift_table[i]);
          String tempright = shift_left(right1, shift_table[i]);
          
          String combine_str = templeft + tempright;
          String round_key = permute(combine_str, key_comp, 48);
          rkb.add(round_key);
          rk.add(bin2hex(round_key));
          // System.out.println(""+(combine_str));
          // System.out.println(""+bin2hex(round_key));
          left1 = templeft;
          right1 = tempright;

      }

    String pt1 = hex2bin(pt);
    String pt2  = permute(pt1, initial_perm, 64);
    // System.out.println("After initial permutation ,"+ bin2hex(pt2));

    String left = pt2.substring(0,32);
   
    String right  = pt2.substring(32, 64);

    for(int i=0;i<16;i++){
          String right_expanded = permute(right, exp_d, 48);
          String temp1 = rkb.get(i);
          String xor_x = xor(right_expanded,temp1);
          StringBuilder sbox_str = new StringBuilder();
          for(int j=0;j<8;j++){
            String xrow = ""+ xor_x.charAt(j*6)+xor_x.charAt(j*6+5);
            String xcol = ""+ xor_x.charAt(j * 6 + 1)+xor_x.charAt(j * 6 + 2)+xor_x.charAt(j * 6 + 3)+xor_x.charAt(j * 6 + 4);
            // xor_x[j * 6 + 1] + xor_x[j * 6 + 2] + xor_x[j * 6 + 3] + xor_x[j * 6 + 4])
            int row = bin2dec(Integer.parseInt(xrow));
            int col = bin2dec(Integer.parseInt(xcol));
            int val = sbox[j][row][col];
            sbox_str.append(dec2bin(val));

          }
          String sbox_str1 = sbox_str.toString();
          sbox_str1 = permute(sbox_str1, per, 32);
          // System.out.println("~~~~~~~~~~~~~`"+left);
          String result = xor(left,sbox_str1);
          left = result;
          if(i!=15){
            String temp = left;
            left = right;
            right = temp;

          }
          // System.out.println("Round "+(i+1)+" "+bin2hex(left)+ " "+ bin2hex(right)+ " "+rk.get(i));
    }
    String combine  = left + right;
    String cipher_text = permute(combine, final_perm, 64);
    return cipher_text;
  }


  public static String decrypt(String pt,String key){
      String keybin = hex2bin(key);
      String keynew = permute(keybin, keyp, 56);
      // System.out.println("bin is  "+keynew);
      String left1 = keynew.substring(0,28);
      String right1 = keynew.substring(28);
      
     
      ArrayList<String> rkb = new ArrayList<String>();
      ArrayList<String> rk = new ArrayList<String>();
      for(int i=0;i<16;i++){
          String templeft = shift_left(left1, shift_table[i]);
          String tempright = shift_left(right1, shift_table[i]);
          
          String combine_str = templeft + tempright;
          String round_key = permute(combine_str, key_comp, 48);
          rkb.add(round_key);
          rk.add(bin2hex(round_key));
          // System.out.println(""+(combine_str));
          // System.out.println(""+bin2hex(round_key));
          left1 = templeft;
          right1 = tempright;

      }
    Collections.reverse(rkb);
    Collections.reverse(rk);
    String pt1 = hex2bin(pt);
    String pt2  = permute(pt1, initial_perm, 64);
    // System.out.println("After initial permutation ,"+ bin2hex(pt2));

    String left = pt2.substring(0,32);
   
    String right  = pt2.substring(32, 64);

    for(int i=0;i<16;i++){
          String right_expanded = permute(right, exp_d, 48);
          String temp1 = rkb.get(i);
          String xor_x = xor(right_expanded,temp1);
          StringBuilder sbox_str = new StringBuilder();
          for(int j=0;j<8;j++){
            String xrow = ""+ xor_x.charAt(j*6)+xor_x.charAt(j*6+5);
            String xcol = ""+ xor_x.charAt(j * 6 + 1)+xor_x.charAt(j * 6 + 2)+xor_x.charAt(j * 6 + 3)+xor_x.charAt(j * 6 + 4);
            // xor_x[j * 6 + 1] + xor_x[j * 6 + 2] + xor_x[j * 6 + 3] + xor_x[j * 6 + 4])
            int row = bin2dec(Integer.parseInt(xrow));
            int col = bin2dec(Integer.parseInt(xcol));
            int val = sbox[j][row][col];
            sbox_str.append(dec2bin(val));

          }
          String sbox_str1 = sbox_str.toString();
          sbox_str1 = permute(sbox_str1, per, 32);
          // System.out.println("~~~~~~~~~~~~~`"+left);
          String result = xor(left,sbox_str1);
          left = result;
          if(i!=15){
            String temp = left;
            left = right;
            right = temp;

          }
          // System.out.println("Round "+(i+1)+" "+bin2hex(left)+ " "+ bin2hex(right)+ " "+rk.get(i));
    }
    String combine  = left + right;
    String cipher_text = permute(combine, final_perm, 64);
    return cipher_text;
  }

  public static String bin2hex(String s){
    HashMap<String,Character> mydict = new HashMap<>();
    mydict.put("0000", '0');
    mydict.put("0001", '1');
    mydict.put("0010", '2');
    mydict.put("0011", '3');
    mydict.put("0100", '4');
    mydict.put("0101", '5');
    mydict.put("0110", '6');
    mydict.put("0111", '7');
    mydict.put("1000", '8');
    mydict.put("1001", '9');
    mydict.put("1010", 'A');
    mydict.put("1011", 'B');
    mydict.put("1100", 'C');
    mydict.put("1101", 'D');
    mydict.put("1110", 'E');
    mydict.put("1111", 'F');
    String hex = "";
    int n = s.length();
    for(int i = 0;i<n;i+=4){
      hex += mydict.get(s.substring(i,i+4));
    }
    return hex;
  }

  public static String shift_left(String k,int nth_shifts){
    
    StringBuilder s = new StringBuilder();
    int n = k.length();

    for(int i = 0;i<nth_shifts;i++){
        // System.out.println("~~~~~~~"+k);
        for(int j=1;j<n;j++){
           
            s.append(k.charAt(j));
        }
        s.append(k.charAt(0));
        
        k = s.toString();

        s.setLength(0);
      
    }
    return k;
  }

  public static String permute(String key ,int[] arr,int n ){
    String permutation = "";
    for(int i=0;i<n;i++){
      char c = key.charAt(arr[i]-1);
      permutation+=c;
    }
    return permutation;

  }
  public static String hex2bin(String str) {
    Hashtable<Character,String> mydict = new Hashtable<>();

    mydict.put('0', "0000");
    mydict.put('1', "0001");
    mydict.put('2', "0010");
    mydict.put('3', "0011");
    mydict.put('4', "0100");
    mydict.put('5', "0101");
    mydict.put('6', "0110");
    mydict.put('7', "0111");
    mydict.put('8', "1000");
    mydict.put('9', "1001");
    mydict.put('A', "1010");
    mydict.put('B', "1011");
    mydict.put('C', "1100");
    mydict.put('D', "1101");
    mydict.put('E', "1110");
    mydict.put('F', "1111");

    String bin = "";
   
    for(char c:str.toCharArray()){
        bin += mydict.get(c);
    }
    return bin;
  }

  
  public static int add(int x, int y){
    return  x+y;
  }



  static int keyp[] = {57, 49, 41, 33, 25, 17, 9,
		1, 58, 50, 42, 34, 26, 18,
		10, 2, 59, 51, 43, 35, 27,
		19, 11, 3, 60, 52, 44, 36,
		63, 55, 47, 39, 31, 23, 15,
		7, 62, 54, 46, 38, 30, 22,
		14, 6, 61, 53, 45, 37, 29,
		21, 13, 5, 28, 20, 12, 4};

static  int shift_table[] = {1, 1, 2, 2,
			2, 2, 2, 2,
			1, 2, 2, 2,
			2, 2, 2, 1};


static int key_comp[] = {14, 17, 11, 24, 1, 5,
			3, 28, 15, 6, 21, 10,
			23, 19, 12, 4, 26, 8,
			16, 7, 27, 20, 13, 2,
			41, 52, 31, 37, 47, 55,
			30, 40, 51, 45, 33, 48,
			44, 49, 39, 56, 34, 53,
			46, 42, 50, 36, 29, 32};

  public static void main(String[] args) {

      String pt = "123456ABCD132536";
      String key = "AABB09182736CCDD";

      
     System.out.println("Encryption");
     String encrypted = encrypt(pt,key); 
     String cipher_text = bin2hex(encrypted);
     System.out.println(""+cipher_text);

     System.out.println("Decryption");
     
     String text = decrypt(cipher_text,key); 
     String Plain_text = bin2hex(text);
     System.out.println(""+Plain_text);



     
  }  
}
