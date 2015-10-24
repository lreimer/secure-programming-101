package de.qaware.campus.secpro.web.passwords;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Objects;

/**
 * The passwords JSF model bean.
 *
 * @author mario-leander.reimer
 */
@Model
public class Passwords {

    @Inject
    private MasterPassword masterPassword;

    public String getMasterPassword() {
        return Objects.toString(masterPassword);
    }
}
