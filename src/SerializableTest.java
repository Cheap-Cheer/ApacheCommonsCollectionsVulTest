import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("zhangsan", 24);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.txt"));
        oos.writeObject(person);
        oos.close();

        FileInputStream fis = new FileInputStream("test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
        ois.close();
    }
}