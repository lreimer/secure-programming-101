package de.qaware.campus.secpro.web.passwords;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 * A CDI producer implementation of our master password.
 *
 * @author mario-leander.reimer
 */
@ApplicationScoped
public class MasterPasswordProducer {
    /**
     * Returns the master password instance.
     *
     * @return the master password
     */
    @Produces
    @Dependent
    @Default
    public MasterPassword create() {
        return MasterPassword.INSTANCE;
    }
}
