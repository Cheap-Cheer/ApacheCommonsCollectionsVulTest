import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.apache.commons.collections.functors.InvokerTransformer;
import sun.reflect.annotation.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String cmd = "notepad";

        // 构建transformer对象
        InvokerTransformer transformer = new InvokerTransformer(
                "exec", new Class[]{String.class}, new Object[]{cmd}
        );

        // 传入Runtime实例，执行对象转换操作
        transformer.transform(Runtime.getRuntime());
        Runtime.getRuntime().exec("notepad");
    }
}