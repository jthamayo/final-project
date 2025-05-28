package io.github.jthamayo.backend.entity.base;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.proxy.HibernateProxy;

import io.github.jthamayo.backend.entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final long serialVersionUID = 1L;
    
    public Long getId() {
	return id;
    }

    @Override
    public boolean equals(Object o) {

	if (this == o)
	    return true;
	if (o == null)
	    return false;
	if (getEffectiveClass(this) != getEffectiveClass(o))
	    return false;
	User other = (User) o;
	return getId() != null && Objects.equals(getId(), other.getId());

    }

    @Override
    public int hashCode() {
	return getId() != null ? Objects.hash(getEffectiveClass(this), getId()) : 0;
    }

    private static Class<?> getEffectiveClass(Object obj) {
	return obj instanceof HibernateProxy ? ((HibernateProxy) obj).getHibernateLazyInitializer().getPersistentClass()
		: obj.getClass();
    }
}
