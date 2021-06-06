package dynamicproxy.cglib;

import dynamicproxy.Advice;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class ProxyTest {

    public static void main(String[] args) {
        Target target = new Target();
        Advice advice = new Advice();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            advice.before();
            Object invoke = method.invoke(target, objects);
            advice.after();
            return invoke;
        });

        Target proxy = (Target) enhancer.create();
        proxy.save();
        proxy.print("cglib动态代理");
    }
}
