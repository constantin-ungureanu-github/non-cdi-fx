package org.fx.services;

import java.io.File;

public interface PersistenceService {
    public Object load(final File file, final Class<?> clasz);

    public void save(final File file, final Object object);
}
