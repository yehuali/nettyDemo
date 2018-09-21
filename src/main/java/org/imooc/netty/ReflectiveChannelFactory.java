package org.imooc.netty;

public class ReflectiveChannelFactory<T extends Channel> {
    private final Class<? extends  T> clazz;

    public ReflectiveChannelFactory(Class<? extends T> clazz) {
       if(clazz == null){
           throw new NullPointerException("clazz");
       }
        this.clazz = clazz;
    }

    public T newChannel() {
        try {
            return clazz.newInstance();
        } catch (Throwable t) {
            throw new RuntimeException("Unable to create Channel from class " + clazz, t);
        }
    }
}
