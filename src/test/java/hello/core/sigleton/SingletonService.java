package hello.core.sigleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();//static 선언 -> 클래스 영역에 하나만 올라감 final이라 변경도 불가
    //하나의 인스턴스만 생성해서 클래스 영역에 넣어두겠다

    //조회
    public static SingletonService getInstance(){
        return instance;
    }
    private SingletonService(){//생성자를 private으로 선언해 외부에서 생성하지 못하도록 막음 -> 프로그램에 하나의 SingletonService가 존재하는 것

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
