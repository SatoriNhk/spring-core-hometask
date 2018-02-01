package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Properties;
import java.util.Set;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {


    private Set<Auditorium> auditoriumSet;

    @Autowired
    public AuditoriumServiceImpl(Set<Auditorium> auditoriumSet) {
        this.auditoriumSet = auditoriumSet;
    }

    public void setAuditoriumSet(Set<Auditorium> auditoriumSet) {
        this.auditoriumSet = auditoriumSet;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumSet;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumSet.stream().filter((s) -> s.getName().equals(name)).findFirst().orElse(null);
    }
}
