package dynamicproxy.cglib;

import dynamicproxy.jdk.TargetInterface;

public class Target {
    public void save() {
        System.out.println("save running...");
    }

    public void print(String str) {
        System.out.println(str);
    }
}
