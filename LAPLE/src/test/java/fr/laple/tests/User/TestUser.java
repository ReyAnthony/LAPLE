package fr.laple.tests.user;

import fr.laple.model.user.User;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by zaafranigabriel on 06/06/2015.
 */

public class TestUser {
    @Test
    public void createUser(){
        Assert.assertNotNull(User.getInstance());
  }

}
