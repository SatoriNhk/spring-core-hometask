package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public class AuditoriumServiceImpl implements AuditoriumService {
    private Set<Auditorium> auditoriumSet;

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

        /*Auditorium auditorium;
        for (Auditorium aud : auditoriumSet) {
            String audName = aud.getName();
            if (audName.equals(name)) {
                auditorium = aud;
            }
        }
        return auditorium;*/
    }
}
