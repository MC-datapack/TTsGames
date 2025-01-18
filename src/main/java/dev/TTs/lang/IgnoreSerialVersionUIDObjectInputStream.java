package dev.TTs.lang;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class IgnoreSerialVersionUIDObjectInputStream extends ObjectInputStream {

    public IgnoreSerialVersionUIDObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();
        Class<?> localClass = null;
        try {
            localClass = Class.forName(resultClassDescriptor.getName());
        } catch (ClassNotFoundException ignored) {}

        if (localClass != null) {
            ObjectStreamClass localClassDescriptor = ObjectStreamClass.lookup(localClass);
            if (localClassDescriptor != null) {
                long resultSerialVersionUID = resultClassDescriptor.getSerialVersionUID();
                long localSerialVersionUID = localClassDescriptor.getSerialVersionUID();
                if (resultSerialVersionUID != localSerialVersionUID) {
                    resultClassDescriptor = localClassDescriptor;
                }
            }
        }
        return resultClassDescriptor;
    }
}
