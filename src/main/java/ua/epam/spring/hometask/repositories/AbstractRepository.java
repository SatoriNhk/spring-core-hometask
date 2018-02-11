package ua.epam.spring.hometask.repositories;

import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface AbstractRepository<T> {

    T save(@Nonnull T object);

    void remove(@Nonnull T object);

    @Nullable
    T getById(@Nonnull Long id);
    
    List<T> getAll();
}
