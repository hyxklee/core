package hello.core.sigleton;

public class StatefulService {
    
//    private int price;// 상태를 유지하는 필드
    
//    public void order(String name, int price){//stateful한 상태
//        System.out.println("name = " + name + " price = "+price);
//        this.price = price;// 문제발생
//    }
    public int order(String name, int price){
        System.out.println("name = " + name + " price = "+price);
//        this.price = price;// 문제발생
        return price;
    }

    public void getPrice() {
//        return price;
    }
}
