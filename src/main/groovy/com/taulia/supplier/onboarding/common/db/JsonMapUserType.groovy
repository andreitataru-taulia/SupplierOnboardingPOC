package com.taulia.supplier.onboarding.common.db

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.UserType

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

class JsonMapUserType implements UserType {
    @Override
    int[] sqlTypes() {
        return new int[]{Types.OTHER}
    }

    @Override
    Class returnedClass() {
        return Map.class
    }

    @Override
    boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true
        } else if (x == null || y == null) {
            return false
        } else {
            return x.equals(y)
        }
    }

    @Override
    int hashCode(Object x) throws HibernateException {
        return x.hashCode()
    }

    @Override
    Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String json = rs.getString(names[0])
        if (json != null && !json.isEmpty()) {
            return JsonHelper.fromJson(json, Map.class)
        }
        return new HashMap<>()
    }

    @Override
    void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value != null) {
            if (value instanceof Map && ((Map) value).isEmpty()) {
                st.setObject(index, JsonHelper.toJson(value), Types.OTHER)
                return
            }
        }

        st.setObject(index, null)
    }

    @Override
    @SuppressWarnings("unchecked")
    Object deepCopy(Object value) throws HibernateException {
        return value == null ? null : new HashMap((Map) value)
    }

    @Override
    boolean isMutable() {
        return true
    }

    @Override
    Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value)
    }

    @Override
    Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached)
    }

    @Override
    Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original)
    }
}
