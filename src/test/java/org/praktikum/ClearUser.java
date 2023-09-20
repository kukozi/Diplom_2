package org.praktikum;

import org.junit.After;
import org.praktikum.methods.delete.user.DeleteUser;

public class ClearUser {
    protected String token;
    DeleteUser delete = new DeleteUser();

    @After
    public void deleteUser() {
        delete.deleteUser(token);
    }
}
