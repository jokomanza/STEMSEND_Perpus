package form;

/**
 *
 * @author Luciver
 *
 */
public class indexOf {
    public static void main(String []a){
        String kata = "Lucifer";
        int index = kata.indexOf("f");
        int Indx2 = kata.indexOf("c", 1);
//        System.out.println(index);
//        System.out.println(Indx2);
////        IndexOf();
        compareTo();
    }
    
    static void IndexOf(){
        String phoneNum = "404-543-2345";
        int idx1 = phoneNum.indexOf('-');
        System.out.println("index of first dash: "+ idx1);
        //3
        int idx2 = phoneNum.indexOf('-',4);
        System.out.println("second dash idx: "+idx2); // 7
    }
    
    static void charAt(){
        String src = "Lucifer";
        System.out.println(src.charAt(0));
        System.out.println(src.charAt(2));
    }
    
    static void substring(){
        String src = "Hello, World!";
        System.out.println(src.charAt(5));
        System.out.println(src.substring(0, 5));
        System.out.println(src.charAt(7));
        System.out.println(src.substring(7, 11));
    }
    
    static void replace(){
        String src = "Lucifer";
        System.out.println(src.replace("L", "K"));
    }
    
    static void replaceFirst(){
        String src = "String replace with replaceFirst";
        System.out.println(src.replaceFirst("re", "RE"));
    }
    
    static void concat(){
        String src = "Hello ";
        System.out.println(src.concat("Hanya String"));
    }
    
    static void compareTo(){
        String srcx1 = "computer";
        String srcx2 = "comparison";
        String srcx3 = "Akuma";
        System.out.println(srcx1.compareToIgnoreCase(srcx2));
    }
}
