package dynamicproxy.jdk;

import dynamicproxy.Advice;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        TargetInterfaceImpl target = new TargetInterfaceImpl();
        Advice advice = new Advice();

        TargetInterface proxyInstance = (TargetInterface) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, s) -> {
            advice.before();
            Object invoke = method.invoke(target, s);
            advice.after();
            return invoke;
        });

        proxyInstance.save();
        proxyInstance.print("JDK动态代理");
    }
}
