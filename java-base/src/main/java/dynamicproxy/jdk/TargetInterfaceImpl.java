package dynamicproxy.jdk;

public class TargetInterfaceImpl implements TargetInterface {
    @Override
    public void save() {
        System.out.println("save running...");
    }

    @Override
    public void print(String str) {
        System.out.println(str);
    }
}
