package com.nick.demo;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;

public class TimeElapsedUtil {

    private String interceptMethodName;

    public static <T> T getProxy(Class<?> cls) {
        return getProxy(cls, null);
    }

    public static <T> T getProxy(Class<?> cls, final String interceptMethodName) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallbacks(new Callback[]{new TimeElapseProxy(), new NonPrintTimeElapseProxy()});
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                String methodName = method.getName();
                if (interceptMethodName != null && methodName.equals(interceptMethodName)) {
                    return 0;
                }
                return 1;
            }
        });
        return (T) enhancer.create();
    }

    private static class TimeElapseProxy  implements MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println(o.getClass().getSuperclass().getCanonicalName() + " is calling method: " + methodProxy.getSignature().getName());
            long start = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println(o.getClass().getSuperclass().getCanonicalName()
                    + " finished calling method: "
                    + methodProxy.getSignature().getName()
                    + ", Time Elapsed: "
                    + new BigDecimal((double) (System.nanoTime() - start)/(1000000)).setScale(3 , BigDecimal.ROUND_HALF_UP) + "ms");
            return result;
        }
    }

    private static class NonPrintTimeElapseProxy  implements MethodInterceptor{
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invokeSuper(o, objects);
        }
    }

    public String getInterceptMethodName() {
        return interceptMethodName;
    }

    public void setInterceptMethodName(String interceptMethodName) {
        this.interceptMethodName = interceptMethodName;
    }
}
