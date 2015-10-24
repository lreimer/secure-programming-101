package de.qaware.campus.secpro.web.passwords;

import javax.enterprise.inject.Alternative;

/**
 * A simple utility class to obtain the master password.
 * <p>
 * The master password is stored in a glassfish password alias, and
 * passed in via a standard Java system property.
 * <p>
 * -Dde.qaware.campus.secpro.password=${ALIAS=secpro_password_alias}
 * <p>
 * You need to create the password alias using asadmin create-password-alias
 * command for your Glassfish domain.
 *
 * @author mario-leander.reimer
 */
@Alternative
public final class MasterPassword {
    /**
     * Returns the master password instance.
     */
    public static final MasterPassword INSTANCE = new MasterPassword();

    /**
     * No instantiation.
     */
    private MasterPassword() {
    }

    @Override
    public String toString() {
        return System.getProperty("de.qaware.campus.secpro.password");
    }
}
