package de.lathspell.test.io.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Datentyp der SerializableTest benutzt wird.
 *
 * Damit der ganze Object-Graph serialisiert werden kann muss jede einzelne
 * Klasse die irgendwo als Attribut vor kommt ebenfalls serialisierbar sein!
 *
 * Werden Datentypen verwendet, bei denen das nicht gegeben ist (BunnyColor), so
 * m�ssen diese als "transient" markiert und ggf. �ber readObject/writeObject
 * manuell serialisiert werden. Serializable stammt aus der Zeit in der es noch
 * keine Annotations gab daher ist transient ein Schl�sselwort und gibt es auch
 * keine begleitenden Annotationen wie z.B. @Serialize(using = Custom.class)
 * 
 * Vorsicht: Der Versuch fremde Datentypen zu serialisieren ist gef�hrlich, da
 * zuk�nftige Versionen besagter Klasse neue Attribute hinzubekommen k�nnten,
 * die man hier nicht speichert!
 */
class Bunny implements Serializable {

    private static final long serialVersionUID = -6814203485633565516L;

    private final String name;
    private final BunnySize size;
    private transient BunnyColor color;
    private final int age;
    private final List<String> attrs;

    public Bunny(String name, BunnySize size, BunnyColor color, int age, List<String> attrs) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.age = age;
        this.attrs = attrs;
    }

    @Override
    public String toString() {
        return name + " is " + age + " years old, " + size + " cm tall, " + color + " and has " + attrs;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(color.getColor());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        color = new BunnyColor((String)in.readUTF());
    }

}
