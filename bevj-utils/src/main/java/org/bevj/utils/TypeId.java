package org.bevj.util;

public class TypeId {
    private final Class<?> clazz;
    public static TypeId of(Class<?> clazz) {
        return new TypeId(clazz);
    }

    private TypeId(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TypeId other = (TypeId) obj;
        if (clazz == null) {
            if (other.clazz != null)
                return false;
        } else if (!clazz.equals(other.clazz))
            return false;
        return true;
    }
    
    
}
